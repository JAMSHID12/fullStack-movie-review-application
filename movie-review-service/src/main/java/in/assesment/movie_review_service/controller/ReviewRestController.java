package in.assesment.movie_review_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.assesment.movie_review_service.Dto.MovieByUser;
import in.assesment.movie_review_service.Dto.ResponseDto;
import in.assesment.movie_review_service.Dto.ReviewDto;
import in.assesment.movie_review_service.custom_exceptions.ConflictException;
import in.assesment.movie_review_service.custom_exceptions.NotFoundException;
import in.assesment.movie_review_service.model.Review;
import in.assesment.movie_review_service.model.UserInfo;
import in.assesment.movie_review_service.service.IReviewService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/review")
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewRestController {

	
	@Autowired
	private IReviewService reviewService;
	
	@PostMapping
	public ResponseEntity<ResponseDto<Review>> saveReview(@AuthenticationPrincipal final UserInfo userInfo, @Valid @RequestBody ReviewDto reviewDto){
		try {
            ResponseDto<Review> response = reviewService.createReview(userInfo, reviewDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (NotFoundException e) {
            return new ResponseEntity<>(new ResponseDto<>(null, e.getMessage(), 400), HttpStatus.BAD_REQUEST);
        }catch (ConflictException e) {
            return new ResponseEntity<>(new ResponseDto<>(null, e.getMessage(), 304), HttpStatus.ALREADY_REPORTED);
        }
		catch (RuntimeException e) {
            return new ResponseEntity<>(new ResponseDto<>(null, e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ResponseDto<Review>> getUserInfoById( @PathVariable(required = true) long id){
		try {
			ResponseDto<Review> response = reviewService.getReviewById(id);
	        return ResponseEntity.ok(response);
	    } catch (NotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body(new ResponseDto<>(null, e.getMessage(), 404));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(new ResponseDto<>(null, "An error occurred while fetching review", 500));
	    }
	}
	
	@GetMapping("/by/{id}")
	public ResponseEntity<ResponseDto<Review>> getReviewByMovieAndUser(@AuthenticationPrincipal final UserInfo userinfo, @PathVariable(required = true) long id){
		try {
			ResponseDto<Review> response = reviewService.getReviewByMovieAndUserId(id, userinfo.getId());
	        return ResponseEntity.ok(response);
	    } catch (NotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body(new ResponseDto<>(null, e.getMessage(), 404));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(new ResponseDto<>(null, "An error occurred while fetching review", 500));
	    }
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ResponseDto<Review>> updateReview(@AuthenticationPrincipal final UserInfo userInfo,@PathVariable("id") long id, @RequestBody ReviewDto reviewDto) {
	    try {
	        ResponseDto<Review> response = reviewService.updateReview(id, reviewDto, userInfo);
	        return ResponseEntity.ok(response);
	    } catch (NotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body(new ResponseDto<>(null, e.getMessage(), 404));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(new ResponseDto<>(null, "An error occurred while updating user", 500));
	    }
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ResponseDto<String>> deleteReview(@PathVariable("id") long id) {
	    try {
	        ResponseDto<String> response = reviewService.updateAverageRatingAfterReviewDeletion(id);
	        return ResponseEntity.ok(response);
	    } catch (NotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body(new ResponseDto<>(null, e.getMessage(), 404));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(new ResponseDto<>(null, "An error occurred while deleting review", 500));
	    }
	}
	
	@GetMapping("get-all-review/{movieId}")
	public ResponseEntity<ResponseDto<MovieByUser>> getallReviewBymovieId(@PathVariable(required = true) long movieId){
		try {
	        ResponseDto<MovieByUser> response = reviewService.getAllReviewById(movieId);
	        return ResponseEntity.ok(response);
	    } catch (NotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body(new ResponseDto<>(null, e.getMessage(), 404));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(new ResponseDto<>(null, "Fetching all the datas", 500));
	    }
	}
	
}
