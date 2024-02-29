#### JaCoCO 적용

##### Jacoco 의 역할

- java, kotlin 프로젝트의 코드 커버리지를 측정하는데 사용. 

- 테스트를 실행하여 테스트가 어느 정도 범위의 코드를 실행했고, 어떤 부분이 더 테스트 되어야 하는지 알 수 있는 리포트 생성

##### JUnit5

- 단위, 통합 테스트를 하는 테스트 프레임워크

JaCoCo는 코드 커버리지를 측정하고 리포트를 생성하는 데 사용되며, JUnit 5는 테스트 코드 작성과 실행을 위한 프레임워크로, 두 도구를 함께 사용하여 코드의 품질을 향상시키는 데 도움이 됩니다.

##### Jacoco 플러그인 추가

```groovy
plugins {
  id 'jacoco'
}

jacoco {
  // JaCoCo 버전 지
  toolVersion = '0.8.5'

//  테스트결과 리포트를 저장할 경로 변경
//  default 경로는 $buildDir/reports/jacoco
//  reportsDir = file("$buildDir/customJacocoReportDir")
}
```

##### Gradle task 설정

```groovy
// kotlin DSL
tasks.jacocoTestReport {
  reports {
    // 원하는 리포트를 켜고 끌 수 있습니다.
    html.isEnabled = true
    xml.isEnabled = false
    csv.isEnabled = false

//  각 리포트 타입 마다 리포트 저장 경로를 설정할 수 있습니다.
//  html.destination = file("$buildDir/jacocoHtml")
//  xml.destination = file("$buildDir/jacoco.xml")
// "$buildDir"는 프로젝트의 "build" 디렉토
  }
}

// Jacoco Test Coverage Verification 플러그인을 설정하는 블록입니다.
tasks.jacocoTestCoverageVerification {
  violationRules {  //위반 규칙을 설정하는 블록   
    rule {         //위반 규칙을 정의하는 블록
      element = "CLASS"  //위반 규칙을 정의 할 단위. class 단위로 규칙 적용

      limit {   //커버리지 제한 조건을 설정하는 블록
        counter = "BRANCH"  //커버리지 측정 기준
        value = "COVEREDRATIO"  //커버리지 측정 방식. 커버된 비율
        minimum = "0.90".toBigDecimal()  //최소 커버리지의 비
      }
    }
  }
}
//위 코드는 모든 클래스의 분기 커버리지가 최소 90% 이상 되어야 테스트가 성공적
```

##### 테스트 코드 세팅 및 실행
