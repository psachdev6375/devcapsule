mvn cobertura:cobertura -Dcobertura.report.format=xml -DwithHistory org.pitest:pitest-maven:mutationCoverage sonar:sonar -Dsonar.pitest.mode=reuseReport -Dsonar.scm.provider=git 
