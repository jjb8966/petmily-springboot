package kh.petmily.domain.abandoned_animal;

import kh.petmily.domain.DomainObj;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.sql.Date;

@Getter
@NoArgsConstructor
public class AbandonedAnimal implements DomainObj {

    private int abNumber;
    private int sNumber;
    private int age;
    private float weight;
    private char gender;
    private String name;
    private String species;
    private String kind;
    private String location;
    private String uniqueness;
    private String description;
    private String animalState;
    private String imgPath;
    private Date admissionDate;
    private Blob video;
    
    public AbandonedAnimal(int abNumber, String name, String img, String location, Date admissionDate) {
        this.abNumber = abNumber;
        this.name = name;
        this.imgPath = img;
        this.location = location;
        this.admissionDate = admissionDate;
    }

    public AbandonedAnimal(int abNumber, int sNumber, int age, float weight, char gender, String name, String species, String kind, String location, String uniqueness, String description, String animalState, String imgPath, Date admissionDate, Blob video) {
        this.abNumber = abNumber;
        this.sNumber = sNumber;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
        this.name = name;
        this.species = species;
        this.kind = kind;
        this.location = location;
        this.uniqueness = uniqueness;
        this.description = description;
        this.animalState = animalState;
        this.imgPath = imgPath;
        this.admissionDate = admissionDate;
        this.video = video;
    }
}
