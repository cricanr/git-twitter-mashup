git-twitter-mashup
=================================

A simple console git twitter reactive mashup application.

### Running instructions:
Prerequisites needed: sbt installed
Go to terminal project folder and run:

```sbt clean```

```sbt compile```

```sbt test```

```sbt run```

Another way to run the application is using IDEA Intellij. You should the App scala class (main).

## Used 3rd party libraries from GitHub:
https://github.com/DanielaSfregola/twitter4s for accesing Twitter API
https://circe.github.io/circe/ for encoding and decoding JSON
https://github.com/playframework/play-ws to make rest service calls (I use to make a GitHub API call).
I do not want to include the whole Play framework as we only have here a console app so I chose the
standalone WS client.
http://www.scalactic.org/ - offers a biased abstraction over a result (Good / Bad) to make code easier to reason about
https://github.com/mockito/mockito - mocking framework from Java in order to facilitate unit testing

## Configuration:
In order to access Twitter API I created for myself. In the configuration file I just put XXXX which needs
to be replaced with actual values for security reasons as also requested.

twitter {
  consumer {
    key = "XXXX"
    secret = "XXXX"
  }
  access {
    key = "XXXX"
    secret = "XXXX"
  }
}

Created case classes for GitHub and Twitter configs to better structure the code.

## Note:
I have only unit tested the GitHubRepositories decoding as this was custom defined, the rest of the
JSON decoding / encoding was done semi-automatic way by circe, so it would be more like testing the framework
which is not a good idea.
Decided on using twitter4s in order to not re-invent the o-auth twitter API REST implementation details.

## Further improvements possible:
- Could introduce logback and print messages this way, instead of using println statements
- Mashup could refactore and return RepositoryTweetsSummary so that it could in the future easily
used in another context where for example we put on a website the results instead of println statements
