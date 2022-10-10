# kruggerChallenge
Krugger Challenge Test Inventory Vaccine

test {
useJUnitPlatform()
testLogging {
events 'passed', 'skipped', 'failed'
exceptionFormat = 'full'
}
exclude '**/com/krugger/challenge/KruggerChallengeApplicationTest'
}

task integrationTest(type: Test) {
testLogging {
events 'passed', 'skipped', 'failed'
exceptionFormat = 'full'
}
}

sonarqube {
properties {
property 'sonar.coverage.exclusions', '**/com/krugger/challenge/presentation/presenter/**, **/com/krugger/challenge/presentation/controller/**, **/com/krugger/challenge/KruggerChallengeApplication*, **/com/krugger/challenge/repository/**'
}
}

jacocoTestCoverageVerification {
violationRules {
rule {
element = 'CLASS'
excludes = ['KruggerChallengeApplication*']
limit {
minimum = 0.95
}
excludes = [
'com.krugger.challenge.KruggerChallengeApplication',
'com.krugger.challenge.enumerator.**',
'com.krugger.challenge.presentation.presenter.**',
'com.krugger.challenge.presentation.controller.**',
'com.krugger.challenge.entity.**',
'com.krugger.challenge.repository.**'
]
}
}
}

jacocoTestReport {
reports {
html.destination file('build/jacocoHtml')
}
afterEvaluate {
getClassDirectories().setFrom(classDirectories.files.collect {
fileTree(dir: it, exclude: [
'**/com/krugger/challenge/KruggerChallengeApplication*',
'**/com/krugger/challenge/presentation/presenter*',
'**/com/krugger/challenge/presentation/controller*',
'**/com/alquimiasoft/mispedidos/listener**',
'**/com/krugger/challenge/repository/**'
])
})
}
}

pitest {
threads = 2
mutationThreshold = 90
outputFormats = ['XML', 'HTML']
timestampedReports = false
targetClasses = ['com.krugger.challenge.*']
excludedClasses = ['com.krugger.challenge.KruggerChallengeApplication',
'com.krugger.challenge.enumerator.**',
'com.krugger.challenge.presentation.presenter.**',
'com.krugger.challenge.presentation.controller.**',
'com.krugger.challenge.entity.**',
'com.krugger.challenge.repository.**']
excludedTestClasses = ['*IntegrationTest']
}


