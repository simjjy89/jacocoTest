#### JaCoCO 적용

##### Jacoco 의 역할

- java, kotlin 프로젝트의 코드 커버리지 기준을 설정 하고 측정. 

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



##### test task 실행시 JaCoCo task 연동



##### 커버리지 기준 설정

```kotlin
tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            // 'element'가 없으면 프로젝트의 전체 파일을 합친 값을 기준으로 한다.
            limit {
                // 'counter'를 지정하지 않으면 default는 'INSTRUCTION'
                // 'value'를 지정하지 않으면 default는 'COVEREDRATIO'
                minimum = "0.30".toBigDecimal()
            }
        }

        rule {
            // 룰을 간단히 켜고 끌 수 있다.
            enabled = true

            // 룰을 체크할 단위는 클래스 단위
            element = "CLASS"

            // 브랜치 커버리지를 최소한 90% 만족시켜야 한다.
            limit {
                counter = "BRANCH"
                value = "COVEREDRATIO"
                minimum = "0.90".toBigDecimal()
            }

            // 라인 커버리지를 최소한 80% 만족시켜야 한다.
            limit {
                counter = "LINE"
                value = "COVEREDRATIO"
                minimum = "0.80".toBigDecimal()
            }

            // 빈 줄을 제외한 코드의 라인수를 최대 200라인으로 제한한다.
            limit {
                counter = "LINE"
                value = "TOTALCOUNT"
                maximum = "200".toBigDecimal()
//              maximum = "8".toBigDecimal()
            }

            // 커버리지 체크를 제외할 클래스들
            excludes = listOf(
//                    "*.test.*",
//                    "*.Kotlin*"
            )
        }
    }
}
```

커버리지 체크 기준(element)

1. **CLASS (클래스):**
   
   - 클래스 레벨의 커버리지를 나타냅니다.
   - 각 클래스의 전체 코드가 테스트되었는지 여부를 확인합니다.

2. **METHOD (메서드):**
   
   - 메서드 레벨의 커버리지를 나타냅니다.
   - 각 메서드가 호출되었는지 여부를 확인합니다.

3. **LINE (라인):**
   
   - 각 소스 코드 라인의 커버리지를 나타냅니다.
   - 특정 라인이 실행되었는지 여부를 확인합니다.

4. **BRANCH (분기):**
   
   - 조건 분기에 대한 커버리지를 나타냅니다.
   - if문이나 switch문과 같은 조건문에서 조건의 모든 가능한 경로가 테스트되었는지 여부를 판단할 때 사용됩니다.

5. **INSTRUCTION (명령어):**
   
   - 바이트 코드 레벨에서의 각 명령어의 실행 여부를 나타냅니다.
   - 보통은 클래스 및 메서드 커버리지로 충분하지만, 특정한 경우에 직접 명령어 레벨의 커버리지를 확인할 수 있습니다.

6. **COMPLEXITY (복잡성):**
   
   - 코드의 복잡성에 대한 커버리지를 나타냅니다.
   - 코드에서 경로의 복잡성이 얼마나 많은지를 확인할 때 사용됩니다.

7. **METHODGROUP (메서드 그룹):**
   
   - 동일한 메서드 그룹에 속한 메서드들의 커버리지를 나타냅니다.
   - 특별한 상황에서 사용되며, 일반적으로는 `METHOD`를 사용합니다.

8. **SOURCEFILE (소스 파일):**
   
   - 각 소스 파일의 커버리지를 나타냅니다.
   - 특정 소스 파일이 테스트되었는지 여부를 확인할 때 사용됩니다.

9. **PACKAGE (패키지):**
   
   - 패키지 레벨의 커버리지를 나타냅니다.
   - 프로젝트 내의 각 패키지에 대한 테스트된 코드의 비율을 확인할 수 있습니다.
   
   

이러한 다양한 요소들을 사용하여 Jacoco는 프로젝트의 테스트 커버리지를 다양한 수준에서 분석하고, 빌드 보고서를 생성합니다. 일반적으로는 `CLASS`, `METHOD`, `LINE`이 가장 많이 사용되며, 특별한 상황에서 다른 요소들을 사용하여 보다 정교한 분석이 가능합니다.
