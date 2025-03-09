package ruslan.simakov.aigeneratedchat.model;


public record User(
        String id,
        String firstName,
        String lastName,
        int age,
        String bio,
        String imageUrl) {
}
