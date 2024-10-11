package in.assesment.movie_review_service.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieByUser {

	private int userId;
	private int movieId;
	private String description;
	private String duration;
	private String movieImageUrl;
	private String releaseDate;
	private boolean isActive;
	private String title;
	private int totalRatedCount;
	private double totalRating;
	private int movieCreatedUser;
	private String userName;
	private String email;

	List<ReviewOnly> reviews;

}
