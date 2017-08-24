package com.apis.gitTwitterMashup

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.apis.gitTwitterMashup.git.{GitHubClient, GitHubRepository}
import com.apis.gitTwitterMashup.twitter.TwitterClient
import play.api.libs.ws.ahc._

import scala.concurrent.ExecutionContext.Implicits.global

object App {
  private val query = "reactive"

  def main(args: Array[String]) {
    print("Hello basic-project!")

    implicit val system = ActorSystem()
    system.registerOnTermination {
      System.exit(0)
    }
    implicit val materializer = ActorMaterializer()

    val wsClient = StandaloneAhcWSClient()

    val json =
      """{
                                     "total_count": 10104,
                                     "incomplete_results": false,
                                     "items": [
                                         {
                                             "id": 6430758,
                                             "name": "reactive",
                                             "full_name": "component/reactive",
                                             "owner": {
                                                 "login": "component",
                                                 "id": 1687071,
                                                 "avatar_url": "https://avatars1.githubusercontent.com/u/1687071?v=4",
                                                 "gravatar_id": "",
                                                 "url": "https://api.github.com/users/component",
                                                 "html_url": "https://github.com/component",
                                                 "followers_url": "https://api.github.com/users/component/followers",
                                                 "following_url": "https://api.github.com/users/component/following{/other_user}",
                                                 "gists_url": "https://api.github.com/users/component/gists{/gist_id}",
                                                 "starred_url": "https://api.github.com/users/component/starred{/owner}{/repo}",
                                                 "subscriptions_url": "https://api.github.com/users/component/subscriptions",
                                                 "organizations_url": "https://api.github.com/users/component/orgs",
                                                 "repos_url": "https://api.github.com/users/component/repos",
                                                 "events_url": "https://api.github.com/users/component/events{/privacy}",
                                                 "received_events_url": "https://api.github.com/users/component/received_events",
                                                 "type": "Organization",
                                                 "site_admin": false
                                             },
                                             "private": false,
                                             "html_url": "https://github.com/component/reactive",
                                             "description": "Tiny reactive template engine",
                                             "fork": false,
                                             "url": "https://api.github.com/repos/component/reactive",
                                             "forks_url": "https://api.github.com/repos/component/reactive/forks",
                                             "keys_url": "https://api.github.com/repos/component/reactive/keys{/key_id}",
                                             "collaborators_url": "https://api.github.com/repos/component/reactive/collaborators{/collaborator}",
                                             "teams_url": "https://api.github.com/repos/component/reactive/teams",
                                             "hooks_url": "https://api.github.com/repos/component/reactive/hooks",
                                             "issue_events_url": "https://api.github.com/repos/component/reactive/issues/events{/number}",
                                             "events_url": "https://api.github.com/repos/component/reactive/events",
                                             "assignees_url": "https://api.github.com/repos/component/reactive/assignees{/user}",
                                             "branches_url": "https://api.github.com/repos/component/reactive/branches{/branch}",
                                             "tags_url": "https://api.github.com/repos/component/reactive/tags",
                                             "blobs_url": "https://api.github.com/repos/component/reactive/git/blobs{/sha}",
                                             "git_tags_url": "https://api.github.com/repos/component/reactive/git/tags{/sha}",
                                             "git_refs_url": "https://api.github.com/repos/component/reactive/git/refs{/sha}",
                                             "trees_url": "https://api.github.com/repos/component/reactive/git/trees{/sha}",
                                             "statuses_url": "https://api.github.com/repos/component/reactive/statuses/{sha}",
                                             "languages_url": "https://api.github.com/repos/component/reactive/languages",
                                             "stargazers_url": "https://api.github.com/repos/component/reactive/stargazers",
                                             "contributors_url": "https://api.github.com/repos/component/reactive/contributors",
                                             "subscribers_url": "https://api.github.com/repos/component/reactive/subscribers",
                                             "subscription_url": "https://api.github.com/repos/component/reactive/subscription",
                                             "commits_url": "https://api.github.com/repos/component/reactive/commits{/sha}",
                                             "git_commits_url": "https://api.github.com/repos/component/reactive/git/commits{/sha}",
                                             "comments_url": "https://api.github.com/repos/component/reactive/comments{/number}",
                                             "issue_comment_url": "https://api.github.com/repos/component/reactive/issues/comments{/number}",
                                             "contents_url": "https://api.github.com/repos/component/reactive/contents/{+path}",
                                             "compare_url": "https://api.github.com/repos/component/reactive/compare/{base}...{head}",
                                             "merges_url": "https://api.github.com/repos/component/reactive/merges",
                                             "archive_url": "https://api.github.com/repos/component/reactive/{archive_format}{/ref}",
                                             "downloads_url": "https://api.github.com/repos/component/reactive/downloads",
                                             "issues_url": "https://api.github.com/repos/component/reactive/issues{/number}",
                                             "pulls_url": "https://api.github.com/repos/component/reactive/pulls{/number}",
                                             "milestones_url": "https://api.github.com/repos/component/reactive/milestones{/number}",
                                             "notifications_url": "https://api.github.com/repos/component/reactive/notifications{?since,all,participating}",
                                             "labels_url": "https://api.github.com/repos/component/reactive/labels{/name}",
                                             "releases_url": "https://api.github.com/repos/component/reactive/releases{/id}",
                                             "deployments_url": "https://api.github.com/repos/component/reactive/deployments",
                                             "created_at": "2012-10-28T18:47:31Z",
                                             "updated_at": "2017-07-28T03:11:48Z",
                                             "pushed_at": "2017-03-16T09:43:53Z",
                                             "git_url": "git://github.com/component/reactive.git",
                                             "ssh_url": "git@github.com:component/reactive.git",
                                             "clone_url": "https://github.com/component/reactive.git",
                                             "svn_url": "https://github.com/component/reactive",
                                             "homepage": null,
                                             "size": 364,
                                             "stargazers_count": 370,
                                             "watchers_count": 370,
                                             "language": "JavaScript",
                                             "has_issues": true,
                                             "has_projects": true,
                                             "has_downloads": true,
                                             "has_wiki": true,
                                             "has_pages": true,
                                             "forks_count": 51,
                                             "mirror_url": null,
                                             "open_issues_count": 13,
                                             "forks": 51,
                                             "open_issues": 13,
                                             "watchers": 370,
                                             "default_branch": "master",
                                             "score": 97.50721
                                         },
                                           {
             "id": 94343410,
             "name": "reactive",
             "full_name": "tower120/reactive",
             "owner": {
                 "login": "tower120",
                 "id": 2050822,
                 "avatar_url": "https://avatars1.githubusercontent.com/u/2050822?v=4",
                 "gravatar_id": "",
                 "url": "https://api.github.com/users/tower120",
                 "html_url": "https://github.com/tower120",
                 "followers_url": "https://api.github.com/users/tower120/followers",
                 "following_url": "https://api.github.com/users/tower120/following{/other_user}",
                 "gists_url": "https://api.github.com/users/tower120/gists{/gist_id}",
                 "starred_url": "https://api.github.com/users/tower120/starred{/owner}{/repo}",
                 "subscriptions_url": "https://api.github.com/users/tower120/subscriptions",
                 "organizations_url": "https://api.github.com/users/tower120/orgs",
                 "repos_url": "https://api.github.com/users/tower120/repos",
                 "events_url": "https://api.github.com/users/tower120/events{/privacy}",
                 "received_events_url": "https://api.github.com/users/tower120/received_events",
                 "type": "User",
                 "site_admin": false
             },
             "private": false,
             "html_url": "https://github.com/tower120/reactive",
             "description": "Simple, non intrusive reactive programming library for C++. (Events + Observable Properties + Reactive Properties)",
             "fork": false,
             "url": "https://api.github.com/repos/tower120/reactive",
             "forks_url": "https://api.github.com/repos/tower120/reactive/forks",
             "keys_url": "https://api.github.com/repos/tower120/reactive/keys{/key_id}",
             "collaborators_url": "https://api.github.com/repos/tower120/reactive/collaborators{/collaborator}",
             "teams_url": "https://api.github.com/repos/tower120/reactive/teams",
             "hooks_url": "https://api.github.com/repos/tower120/reactive/hooks",
             "issue_events_url": "https://api.github.com/repos/tower120/reactive/issues/events{/number}",
             "events_url": "https://api.github.com/repos/tower120/reactive/events",
             "assignees_url": "https://api.github.com/repos/tower120/reactive/assignees{/user}",
             "branches_url": "https://api.github.com/repos/tower120/reactive/branches{/branch}",
             "tags_url": "https://api.github.com/repos/tower120/reactive/tags",
             "blobs_url": "https://api.github.com/repos/tower120/reactive/git/blobs{/sha}",
             "git_tags_url": "https://api.github.com/repos/tower120/reactive/git/tags{/sha}",
             "git_refs_url": "https://api.github.com/repos/tower120/reactive/git/refs{/sha}",
             "trees_url": "https://api.github.com/repos/tower120/reactive/git/trees{/sha}",
             "statuses_url": "https://api.github.com/repos/tower120/reactive/statuses/{sha}",
             "languages_url": "https://api.github.com/repos/tower120/reactive/languages",
             "stargazers_url": "https://api.github.com/repos/tower120/reactive/stargazers",
             "contributors_url": "https://api.github.com/repos/tower120/reactive/contributors",
             "subscribers_url": "https://api.github.com/repos/tower120/reactive/subscribers",
             "subscription_url": "https://api.github.com/repos/tower120/reactive/subscription",
             "commits_url": "https://api.github.com/repos/tower120/reactive/commits{/sha}",
             "git_commits_url": "https://api.github.com/repos/tower120/reactive/git/commits{/sha}",
             "comments_url": "https://api.github.com/repos/tower120/reactive/comments{/number}",
             "issue_comment_url": "https://api.github.com/repos/tower120/reactive/issues/comments{/number}",
             "contents_url": "https://api.github.com/repos/tower120/reactive/contents/{+path}",
             "compare_url": "https://api.github.com/repos/tower120/reactive/compare/{base}...{head}",
             "merges_url": "https://api.github.com/repos/tower120/reactive/merges",
             "archive_url": "https://api.github.com/repos/tower120/reactive/{archive_format}{/ref}",
             "downloads_url": "https://api.github.com/repos/tower120/reactive/downloads",
             "issues_url": "https://api.github.com/repos/tower120/reactive/issues{/number}",
             "pulls_url": "https://api.github.com/repos/tower120/reactive/pulls{/number}",
             "milestones_url": "https://api.github.com/repos/tower120/reactive/milestones{/number}",
             "notifications_url": "https://api.github.com/repos/tower120/reactive/notifications{?since,all,participating}",
             "labels_url": "https://api.github.com/repos/tower120/reactive/labels{/name}",
             "releases_url": "https://api.github.com/repos/tower120/reactive/releases{/id}",
             "deployments_url": "https://api.github.com/repos/tower120/reactive/deployments",
             "created_at": "2017-06-14T15:01:55Z",
             "updated_at": "2017-08-19T11:10:29Z",
             "pushed_at": "2017-07-07T20:15:34Z",
             "git_url": "git://github.com/tower120/reactive.git",
             "ssh_url": "git@github.com:tower120/reactive.git",
             "clone_url": "https://github.com/tower120/reactive.git",
             "svn_url": "https://github.com/tower120/reactive",
             "homepage": null,
             "size": 54,
             "stargazers_count": 27,
             "watchers_count": 27,
             "language": "C++",
             "has_issues": true,
             "has_projects": true,
             "has_downloads": true,
             "has_wiki": true,
             "has_pages": false,
             "forks_count": 1,
             "mirror_url": null,
             "open_issues_count": 0,
             "forks": 1,
             "open_issues": 0,
             "watchers": 27,
             "default_branch": "master",
             "score": 31.648472
         }]}"""
    val g = GitHubRepository.jsonParse(json)


    val gitHubClient = new GitHubClient(wsClient)
    val gitHubConfig = Configuration.getGitHubConfig
    gitHubClient.getRepositoriesByKeyword(query, gitHubConfig.endpoint, gitHubConfig.timeout)

    val twitterClient = new TwitterClient()
    twitterClient.getTweetsByQuery(query)
      .map(tweets => tweets.foreach(tweet => println(s"Tweet: $tweet")))
      .recover {
        case _: Throwable => println("Failed calling twitter api...")
      }
  }
}
