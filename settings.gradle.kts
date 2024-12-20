pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Divar application"
include(":app")
include(":core")
include(":onboarding")
include(":onboarding:onboarding_presentation")
include(":design_system")
include(":post")
include(":post:post_data")
include(":post:post_domain")
include(":post:post_presentation")
