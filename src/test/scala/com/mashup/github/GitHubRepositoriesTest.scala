package com.mashup.github

import org.scalatest.{MustMatchers, WordSpec}

class GitHubRepositoriesTest extends WordSpec with MustMatchers {
  "The GitHubRepositories entity JSON" when {

    val json =
      """{
            |	"total_count": 1,
            |	"incomplete_results": false,
            |	"items": [{
            |			"id": 6430758,
            |			"name": "reactive",
            |			"full_name": "component/reactive",
            |			"owner": {
            |				"login": "component",
            |				"id": 1687071,
            |				"avatar_url": "https://avatars1.githubusercontent.com/u/1687071?v=4",
            |				"gravatar_id": "",
            |				"url": "https://api.github.com/users/component",
            |				"html_url": "https://github.com/component",
            |				"followers_url": "https://api.github.com/users/component/followers",
            |				"following_url": "https://api.github.com/users/component/following{/other_user}",
            |				"gists_url": "https://api.github.com/users/component/gists{/gist_id}",
            |				"starred_url": "https://api.github.com/users/component/starred{/owner}{/repo}",
            |				"subscriptions_url": "https://api.github.com/users/component/subscriptions",
            |				"organizations_url": "https://api.github.com/users/component/orgs",
            |				"repos_url": "https://api.github.com/users/component/repos",
            |				"events_url": "https://api.github.com/users/component/events{/privacy}",
            |				"received_events_url": "https://api.github.com/users/component/received_events",
            |				"type": "Organization",
            |				"site_admin": false
            |			},
            |			"private": false,
            |			"html_url": "https://github.com/component/reactive",
            |			"description": "Tiny reactive template engine",
            |			"fork": false,
            |			"url": "https://api.github.com/repos/component/reactive",
            |			"forks_url": "https://api.github.com/repos/component/reactive/forks",
            |			"keys_url": "https://api.github.com/repos/component/reactive/keys{/key_id}",
            |			"collaborators_url": "https://api.github.com/repos/component/reactive/collaborators{/collaborator}",
            |			"teams_url": "https://api.github.com/repos/component/reactive/teams",
            |			"hooks_url": "https://api.github.com/repos/component/reactive/hooks",
            |			"issue_events_url": "https://api.github.com/repos/component/reactive/issues/events{/number}",
            |			"events_url": "https://api.github.com/repos/component/reactive/events",
            |			"assignees_url": "https://api.github.com/repos/component/reactive/assignees{/user}",
            |			"branches_url": "https://api.github.com/repos/component/reactive/branches{/branch}",
            |			"tags_url": "https://api.github.com/repos/component/reactive/tags",
            |			"blobs_url": "https://api.github.com/repos/component/reactive/git/blobs{/sha}",
            |			"git_tags_url": "https://api.github.com/repos/component/reactive/git/tags{/sha}",
            |			"git_refs_url": "https://api.github.com/repos/component/reactive/git/refs{/sha}",
            |			"trees_url": "https://api.github.com/repos/component/reactive/git/trees{/sha}",
            |			"statuses_url": "https://api.github.com/repos/component/reactive/statuses/{sha}",
            |			"languages_url": "https://api.github.com/repos/component/reactive/languages",
            |			"stargazers_url": "https://api.github.com/repos/component/reactive/stargazers",
            |			"contributors_url": "https://api.github.com/repos/component/reactive/contributors",
            |			"subscribers_url": "https://api.github.com/repos/component/reactive/subscribers",
            |			"subscription_url": "https://api.github.com/repos/component/reactive/subscription",
            |			"commits_url": "https://api.github.com/repos/component/reactive/commits{/sha}",
            |			"git_commits_url": "https://api.github.com/repos/component/reactive/git/commits{/sha}",
            |			"comments_url": "https://api.github.com/repos/component/reactive/comments{/number}",
            |			"issue_comment_url": "https://api.github.com/repos/component/reactive/issues/comments{/number}",
            |			"contents_url": "https://api.github.com/repos/component/reactive/contents/{+path}",
            |			"compare_url": "https://api.github.com/repos/component/reactive/compare/{base}...{head}",
            |			"merges_url": "https://api.github.com/repos/component/reactive/merges",
            |			"archive_url": "https://api.github.com/repos/component/reactive/{archive_format}{/ref}",
            |			"downloads_url": "https://api.github.com/repos/component/reactive/downloads",
            |			"issues_url": "https://api.github.com/repos/component/reactive/issues{/number}",
            |			"pulls_url": "https://api.github.com/repos/component/reactive/pulls{/number}",
            |			"milestones_url": "https://api.github.com/repos/component/reactive/milestones{/number}",
            |			"notifications_url": "https://api.github.com/repos/component/reactive/notifications{?since,all,participating}",
            |			"labels_url": "https://api.github.com/repos/component/reactive/labels{/name}",
            |			"releases_url": "https://api.github.com/repos/component/reactive/releases{/id}",
            |			"deployments_url": "https://api.github.com/repos/component/reactive/deployments",
            |			"created_at": "2012-10-28T18:47:31Z",
            |			"updated_at": "2017-07-28T03:11:48Z",
            |			"pushed_at": "2017-03-16T09:43:53Z",
            |			"git_url": "git://github.com/component/reactive.git",
            |			"ssh_url": "git@github.com:component/reactive.git",
            |			"clone_url": "https://github.com/component/reactive.git",
            |			"svn_url": "https://github.com/component/reactive",
            |			"homepage": null,
            |			"size": 364,
            |			"stargazers_count": 370,
            |			"watchers_count": 370,
            |			"language": "JavaScript",
            |			"has_issues": true,
            |			"has_projects": true,
            |			"has_downloads": true,
            |			"has_wiki": true,
            |			"has_pages": true,
            |			"forks_count": 51,
            |			"mirror_url": null,
            |			"open_issues_count": 13,
            |			"forks": 51,
            |			"open_issues": 13,
            |			"watchers": 370,
            |			"default_branch": "master",
            |			"score": 97.50721
            |		}
     ]}""".stripMargin

    "decoded from 1 single valid json" should {
      "return a valid GitHubRepository" in {
        val gitHubRepositories = GitHubRepositories.jsonParse(json)

        gitHubRepositories must be(Right(GitHubRepositories(1, List(GitHubRepository("reactive",
          "https://api.github.com/repos/component/reactive",
          "Tiny reactive template engine",
          97.50721
        )))))
      }
    }
  }
}
