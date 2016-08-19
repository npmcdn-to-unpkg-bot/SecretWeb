package ca.tklab.secret.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import ca.tklab.secret.model.base.BaseEntity;

@Entity
@Table(
		name = "da_article",
		indexes = {
		@Index(name = "da_article_writerId_IX",  columnList="WRITER_ID")
		}
	)

public class Article extends BaseEntity<Long> {

	@TableGenerator(name = "Article_ID_Gen", table = "CM_ID_GEN", pkColumnName = "KEY_NAME", valueColumnName = "NEXT_VAL", pkColumnValue = "article_id", initialValue = 100, allocationSize = 10)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Article_ID_Gen")
	private Long id;

	@Column(name = "WRITER_ID", nullable = false, unique = false, insertable=false, updatable=false)
	private String writerId;

	@Column(name = "image_Url", length = 100, nullable = true, unique = false)
	private String imageUrl;

	@Column(name = "content", columnDefinition = "TEXT", nullable = false, unique = false)
	private String content;

	// @OneToOne(optional=false)
	// @JoinColumn(name = "writerId")
	// private Profile profile;



	@ManyToOne
	@JoinColumn(name = "WRITER_ID", referencedColumnName = "ID")
	private Profile profile;
	

	public Profile getWriter() {
		return profile;
	}
	
	@Override
	public Long getId() {
		return id;
	}

	public String getWriterId() {
		return writerId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getContent() {
		return content;
	}

	

	public static Builder getBuilder() {
		return new Builder();
	}

	public static class Builder {

		private Article article;

		public Builder() {
			article = new Article();
		}

		public Builder writer(String writerId) {
			article.writerId = writerId;
			return this;
		}
		public Builder profile(Profile profile) {
			article.profile = profile;
			return this;
		}

		public Builder imageUrl(String imageUrl) {
			article.imageUrl = imageUrl;
			return this;
		}

		public Builder content(String content) {
			article.content = content;
			return this;
		}

		public Article build() {
			return article;
		}
	}
}
