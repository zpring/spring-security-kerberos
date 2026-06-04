package org.springframework.gradle.maven;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Plugin that applies the Release Train repository if the environment variables are set.
 *
 * @author Stephane Nicoll
 */
public class ReleaseTrainRepositoryPlugin implements Plugin<Project> {

	private static final String RELEASE_TRAIN_REPOSITORY_ENV_PREFIX = "RELEASE_TRAIN_MAVEN_REPOSITORY";

	@Override
	public void apply(Project project) {
		String releaseTrainRepositoryUrl = System.getenv(RELEASE_TRAIN_REPOSITORY_ENV_PREFIX + "_URL");
		if (releaseTrainRepositoryUrl != null) {
			configureReleaseTrainRepository(project, releaseTrainRepositoryUrl);
		}
	}

	private void configureReleaseTrainRepository(Project project, String repositoryUrl) {
		String username = System.getenv(RELEASE_TRAIN_REPOSITORY_ENV_PREFIX + "_USERNAME");
		String password = System.getenv(RELEASE_TRAIN_REPOSITORY_ENV_PREFIX + "_PASSWORD");
		project.getRepositories().maven((repo) -> {
			repo.setUrl(repositoryUrl);
			repo.setName("Release Train");
			repo.credentials((credentials) -> {
				credentials.setUsername(username);
				credentials.setPassword(password);
			});
		});
	}

}
