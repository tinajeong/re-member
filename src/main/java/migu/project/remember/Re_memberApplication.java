package migu.project.remember;

import migu.project.remember.data.Post;
import migu.project.remember.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Re_memberApplication {

	private static final Logger logger = LoggerFactory.getLogger(Re_memberApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Re_memberApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:8080", "http://localhost:3000");
			}
		};
	}

	@Bean
	public CommandLineRunner demo(PostRepository repository) {
		return (args) -> {
//			repository.save(new Post("Today's diary", "it was very cold", "단상"));
//			repository.save(new Post("68BT 키보드 후기", "갈축이라 저소음적축보다 구분감이 있어 타자치는 맛이 있었다. 내가 느끼기엔 찰진맛.", "후기"));
//			repository.save(new Post("오늘 내가 뭘 배울 수 있을까", "맡겨지는 업무에서부터 배울 점을 찾을 수 있다.", "단상"));
//			repository.save(new Post("2022 책읽기 목표: 50권",
//					"벌써 6권째 읽고 있다. 정리를 하고 싶은 마음도 들지만 일단은 읽어내려가는 것이 목표이다. 읽기에서 느끼는 것들이 많다.", "단상"));
//			repository.save(new Post("린구에게", "어떤 지식은 직접 경험해야 깨달아지더라고요.  \n" +
//					"무너지거나 분노하거나 빛나거나 가치있을 미래의 순간이 기대가 돼요.\n" +
//					"린구에게 얘기를 할 수 있어 기뻐요. 앞으로도  다양한 이야기 도란도란 나눠봐요. 그게 빠른 시일 안이면 좋겠네요.\n" +
//					"항상 건강하구요. 또 편지 쓸게요.\n" +
//					"ps. 때늦은 생일선물 보냈어요. 향이 좋았어요! 손 소독 자주 할텐데 핸드크림도 발라요!\n" +
//					"\n", "편지"));
//			repository.save(new Post("주히에게", "요즘은 자유에 대해 생각해. \n" +
//					"새소년의 자유는 투쟁끝에 얻어낸 그을린 자유이고, \n" +
//					"악뮤의 자유는 푸른 바닷가를 달리는 얽매이지 않는 자유.\n" +
//					"나의 자유는 '열려있는 것' 같아. 언제고 열어두고 살고 싶어", "편지"));
//
//			logger.info("Posts found with finaAll()");
//			logger.info("----------------------------");
//			for (Post post : repository.findAll()) {
//				logger.info(post.toString());
//
//			}
//			logger.info("----------------------------");
//
//			logger.info("Posts found with findByTitleContaining()");
//			logger.info("----------------------------");
//			for (Post post : repository.findByTitleContaining("에게")) {
//				logger.info(post.toString());
//			}
//			logger.info("----------------------------");

		};

	}
}
