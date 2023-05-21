import java.util.ArrayList;
import java.util.List;

public class Feedback {
    private List<ReviewInstructor> reviews;
    private List<Integer> ratings;

    public Feedback() {
        this.reviews = new ArrayList<>();
        this.ratings = new ArrayList<>();
    }

    public void addReview(ReviewInstructor review) {
        reviews.add(review);
        ratings.add(review.getRating());
    }

    public void removeReview(ReviewInstructor review) {
        ratings.remove(review.getRating());
        reviews.remove(review);
    }

    public List<ReviewInstructor> getAllReviews() {
        return reviews;
    }

    public List<ReviewInstructor> getReviewsByInstructor(Instructor instructor) {
        List<ReviewInstructor> instructorReviews = new ArrayList<>();
        for (ReviewInstructor review : reviews) {
            if (review.getInstructor().equals(instructor)) {
                instructorReviews.add(review);
            }
        }
        return instructorReviews;
    }

    public List<Integer> getAllRatings() {
        return ratings;
    }

    public double calculateOverallScore(Instructor instructor) {
        List<ReviewInstructor> instructorReviews = getReviewsByInstructor(instructor);
        int totalRatings = instructorReviews.size();
        int sumRatings = 0;
        for (ReviewInstructor review : instructorReviews) {
            sumRatings += review.getRating();
        }
        if (totalRatings > 0) {
            return (double) sumRatings / totalRatings;
        } else {
            return 0.0;
        }
    }
}