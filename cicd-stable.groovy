node('linux')
{
  stage ('Poll') {
    // Poll from upstream:
    checkout([
                        $class: 'GitSCM',
                        branches: [[name: '*/main']],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [],
                        userRemoteConfigs: [[url: "https://github.com/IBM/zos-code-page-tools.git"]]])
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/zos-code-page-toolsport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/zos-code-page-toolsport.git'), string(name: 'PORT_DESCRIPTION', value: 'A set of zos code pages tools, handy for conversion from codepages such as EBCDIC and ASCII' ), string(name: 'BUILD_LINE', value: 'STABLE') ]
  }
}
