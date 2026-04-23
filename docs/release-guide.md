# Release 난독화 운영 가이드

## 빌드 원칙

- `debug`: 개발과 테스트용 빌드
- `release`: 배포용 빌드
- `release`에서는 코드 난독화와 리소스 축소가 적용됨

현재 프로젝트 설정 기준:

- Java 컴파일 타겟: `17`
- Kotlin JVM 타겟: `17`
- 난독화: `isMinifyEnabled = true`
- 리소스 축소: `isShrinkResources = true`
- 규칙 파일: [`app/proguard-rules.pro`](/Users/inho/Desktop/BluetoothMessenger_Android/app/proguard-rules.pro:1)

## 배포 전 확인 순서

1. `./gradlew :app:assembleRelease`로 릴리스 빌드가 성공하는지 확인합니다.
2. `app/build/outputs/apk/release/` 또는 AAB 출력 경로에 결과물이 생성됐는지 확인합니다.
3. `app/build/outputs/mapping/release/mapping.txt`가 생성됐는지 확인합니다.
4. 릴리스 빌드 결과와 함께 `mapping.txt`를 별도로 보관합니다.
5. 실제 디바이스에서 주요 화면 진입, DI 주입, 권한 요청, 블루투스 연결 흐름을 점검합니다.

## `mapping.txt`를 보관해야 하는 이유

- 배포 후 크래시 로그는 난독화된 클래스명과 메서드명으로 보일 수 있습니다.
- `mapping.txt`가 있어야 원래 코드 기준으로 역변환할 수 있습니다.
- 릴리스마다 결과가 달라질 수 있으므로 빌드별로 보관하는 것이 안전합니다.

## 운영 팁

- 개발 중에는 `debug` 빌드로 빠르게 확인합니다.
- 배포 직전에는 항상 `release` 빌드를 별도로 검증합니다.
- 난독화 관련 문제는 대체로 `release`에서만 드러나므로, 주요 사용자 흐름은 릴리스 APK 기준으로도 테스트하는 것이 좋습니다.
- 새 라이브러리를 추가했을 때 앱이 `debug`에서는 정상인데 `release`에서만 죽으면 ProGuard/R8 규칙을 먼저 의심합니다.

## 블루투스 기능 추가 전 Keep Rule 체크리스트

블루투스 기능 자체는 Android framework API를 직접 쓰는 경우가 많아서 반드시 많은 keep rule이 필요한 건 아닙니다. 다만 아래 조건이 생기면 규칙이 필요할 수 있습니다.

### 먼저 확인할 것

1. 블루투스 연결 로직이 리플렉션을 사용하는지 확인합니다.
2. 메시지 모델을 JSON 등으로 직렬화하는지 확인합니다.
3. 외부 블루투스 SDK 또는 통신 라이브러리를 붙이는지 확인합니다.
4. Hilt, Room, Gson, kotlinx.serialization 같은 생성 코드 기반 라이브러리를 추가하는지 확인합니다.
5. 릴리스에서만 특정 클래스 생성 실패, 직렬화 실패, 주입 실패가 나는지 확인합니다.

### keep rule이 필요할 가능성이 큰 경우

- 리플렉션으로 클래스 이름을 직접 찾는 코드가 있는 경우
- 애노테이션 메타데이터를 런타임에 읽는 경우
- JSON 직렬화 라이브러리가 필드명이나 기본 생성자에 의존하는 경우
- 외부 SDK 문서에서 ProGuard 예외 규칙을 요구하는 경우
- `ClassNotFoundException`, `NoSuchMethodException`, `JsonDataException`, 주입 실패가 릴리스에서만 발생하는 경우

### 블루투스 메신저에서 특히 보기 좋은 지점

- `BluetoothAdapter`, `BluetoothDevice`, `BluetoothSocket` 같은 Android SDK 클래스 직접 사용
- 메시지 payload 모델
- 채팅 기록 저장 모델
- 권한 상태와 연결 상태를 표현하는 sealed class / enum
- DI 모듈과 주입 대상 클래스

### 추가 규칙을 고려할 때의 순서

1. 먼저 `release` 빌드에서 실제 에러 로그를 확인합니다.
2. 어떤 클래스나 멤버가 사라졌는지 확인합니다.
3. 필요한 타입만 최소 범위로 `-keep` 또는 `-keepclassmembers`를 추가합니다.
4. 너무 넓게 전체 패키지를 keep하지 않습니다.
5. 규칙 추가 후 다시 `assembleRelease`와 실제 디바이스 테스트를 진행합니다.

### 예시로 자주 검토하는 규칙

이 예시는 바로 적용하라는 뜻이 아니라, 아래 상황에서 검토할 수 있는 형태입니다.

```proguard
# JSON 직렬화 모델이 릴리스에서 깨질 때 검토
-keep class com.app.bluetoothmessenger_android.data.model.** { *; }

# Hilt 모듈이나 애노테이션 메타데이터 문제가 있을 때 검토
-keepattributes RuntimeVisibleAnnotations,RuntimeInvisibleAnnotations,AnnotationDefault,InnerClasses,EnclosingMethod,Signature

# 외부 SDK 문서가 특정 패키지 keep를 요구할 때 검토
-keep class com.some.sdk.** { *; }
```

## 블루투스 기능 붙인 뒤 릴리스에서 꼭 확인할 항목

- 기기 검색이 정상 동작하는지
- 기기 선택 후 연결이 되는지
- 연결 후 메시지 송수신이 되는지
- 앱 재실행 후에도 주요 화면이 정상 진입하는지
- 난독화된 릴리스에서만 발생하는 예외가 없는지
