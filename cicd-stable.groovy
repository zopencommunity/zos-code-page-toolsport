node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/ZOSOpenTools/zos-code-page-toolsport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/ZOSOpenTools/zos-code-page-toolsport.git'), string(name: 'PORT_DESCRIPTION', value: 'A set of zos code pages tools, handy for conversion from codepages such as EBCDIC and ASCII' ), string(name: 'BUILD_LINE', value: 'STABLE') ]
  }
}
