package apiPayload;

public class Pet {

    Category category;
    Tags tags;
    int petId;
    String name;
    String photoURLS;
    String status;

    public int getPetId() {
        return petId;
    }

    public void setPetId (int petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoURLS() {
        return photoURLS;
    }

    public void setPhotoURLS(String photoURLS) {
        this.photoURLS = photoURLS;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void getCategory() {
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void getTags() {
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }
}
