/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmanagement;

/**
 * @author DLawrence
 */
public class ClientRecord {
     
    //Client
    private String firstName = "";
    private String middleName = "";
    private String lastName = "";
    private String preferredName = "";
    private String birthDate = "";
    private String birthPlace = "";
    private String email = "";
    private String address = "";
    private String mailingAddress = "";
    private String homeAddress = "";
    private String phone = "";
    private String introducedBy = "";
    private String notes = "";
    
    private String occupation = "";
    private String rating = "";
    private String type = "";
    private String source = "";
    private String gradYear = "";
    private String stage = "";
    
    //Transitions
    private String divorce = "";
    private String divorceNotes = "";
    private String retirement = "";
    private String retirementNotes = "";
    private String intergenerationalInheritance = "";
    private String iiNotes = "";
    private String spousalInheritance = "";
    private String siNotes = "";
    
    private String lastContact = "";
    private String nextContact = "";
    private String quickContactNotes = "";
    
    //Relationships
    private String pets = "";
    private String petNotes = "";
    private String previousJobs = "";
    private String almaMater = "";
    private String previousMarriage = "";
    private String health = "";
    
    private String type1_MT = "";
    private String type2_MT = "";
    private String date1_MT = "";
    private String date2_MT = "";
    
    //Relationships - Personal
    private String firstNamePersonal = "";
    private String lastNamePersonal = "";
    private String relationshipPersonal = "";
    private String agePersonal = "";
    private String notesPersonal = "";
    
    private String type_AI = "";
    private String opened = "";
    private String company = "";
    private String benefit = "";
    private boolean FPO_BU;
    
    //Relationships - Professional
    private String firstNameProfessional = "";
    private String lastNameProfessional = "";
    private String relationshipProfessional = "";
    private String ageProfessional = "";
    private String notesProfessional = "";
    
    //Favorites
    private String drinks = "";
    private String restaurants = "";
    private String giftCards = "";
    private String snacks = "";
    private String fruit = "";
    private String flower = "";
    private String travelPlaces = "";
    private String hobbies = "";
    private String charities = "";
    private String volunteerActivities = "";
    private String books = "";
    private String blogs = "";
    private String websites = "";
    private String memberships = "";
    private String organizations = "";
    
    //Action Items
    
    //Action Items - Extended
    
    ClientRecord(){
       
    }
    
    ClientRecord(String firstname, String lastName, String email, String phone, String occupation,
                String rating, String type, String source, String gradYear, String stage, String lastContact,
                String nextContact, String quickContactNotes, String type1_MT, String type2_MT, String date1_MT,
                String date2_MT, String type_AI, String opened, String company, String benefit, boolean FPO_BU){
        
    this.firstName = firstname;   
    this.middleName = middleName;
    this.lastName = lastName;
    this.preferredName = preferredName;
    this.birthDate = birthDate;
    this.birthPlace = birthPlace;
    this.email = email;
    this.address = address;
    this.mailingAddress = mailingAddress;
    this.homeAddress = homeAddress;
    this.phone = phone;
    this.introducedBy = introducedBy;
    this.notes = notes;
    
    this.divorce = divorce;
    this.divorceNotes = divorceNotes;
    this.retirement = retirement;
    this.retirementNotes = retirementNotes;
    this.intergenerationalInheritance = intergenerationalInheritance;
    this.iiNotes = iiNotes;
    this.spousalInheritance = spousalInheritance;
    this.siNotes = siNotes;
    
    this.pets = pets;
    this.petNotes = petNotes;
    this.previousJobs = previousJobs;
    this.almaMater = almaMater;
    this.previousMarriage = previousMarriage;
    this.health = health;
    
    this.firstNamePersonal = firstNamePersonal;
    this.lastNamePersonal = lastNamePersonal;
    this.relationshipPersonal = relationshipPersonal;
    this.agePersonal = agePersonal;
    this.notesPersonal = notesPersonal;
    
    this.firstNameProfessional = firstNameProfessional;
    this.lastNameProfessional = lastNameProfessional;
    this.relationshipProfessional = relationshipProfessional;
    this.ageProfessional = ageProfessional;
    this.notesProfessional = notesProfessional;
    
    this.drinks = drinks;
    this.restaurants = restaurants;
    this.giftCards = giftCards;
    this.snacks = snacks;
    this.fruit = fruit;
    this.flower = flower;
    this.travelPlaces = travelPlaces;
    this.hobbies = hobbies;
    this.charities = charities;
    this.volunteerActivities = volunteerActivities;
    this.books = books;
    this.blogs = blogs;
    this.websites = websites;
    this.memberships = memberships;
    this.organizations = organizations;
    
    
    this.occupation = occupation;
    this.rating = rating;
    this.type = type;
    this.source = source;
    this.gradYear = gradYear;
    this.stage = stage;
    this.lastContact = lastContact;
    this.nextContact = nextContact;
    this.quickContactNotes = quickContactNotes;
    this.type1_MT = type1_MT;
    this.type2_MT = type2_MT;
    this.date1_MT = date1_MT;
    this.date2_MT = date2_MT;
    this.type_AI = type_AI;
    this.opened = opened;
    this.company = company;
    this.benefit = benefit;
    this.FPO_BU = FPO_BU;
          
    }
    
    public void submitRecord(){
        Database submitNewRecord = new Database();
        submitNewRecord.addElement(email,phone,firstName,lastName);
    }
    
    //the "get" methods are currently only used for testing purposes
    public String getFirstName(){
        return firstName;
    }
    public String getRating(){
        return rating;
    }
    public boolean getFPO(){
        return FPO_BU;
    }
      
}
