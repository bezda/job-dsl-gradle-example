String basePath = 'example9'
String repo = ':pserver:anoncvs@example.org:/cvsroot'

folder(basePath) {
    description 'This example shows how to use the automatically generated DSL provided by other plugins.'
}

job("$basePath/gradle-legacy-build") {
    scm {
        git {
            remote {
                github repo
                refspec '+refs/pull/*:refs/remotes/origin/pr/*'
            }
            branch '${sha1}'
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    steps {
        gradle 'assemble'
    }
}

folder('images-folder') {
    displayName('Docker Images')

    properties {
        /*
        folderLibraries { // <-- NOTE: provided by testPlugins dependency in build.gradle
            libraries {
                libraryConfiguration {
                    name 'test'
                    retriever {
                    }
                }
            }
        }
         */
    }
}