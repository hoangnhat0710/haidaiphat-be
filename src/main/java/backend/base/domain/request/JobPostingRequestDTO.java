package backend.base.domain.request;

import java.time.LocalDateTime;

public class JobPostingRequestDTO {
    private String title;
    private String city;
    private String workingType;
    private Integer quantity;
    private String salary;
    private String genderRequirement;
    private String experienceRequirement;
    private String jobDetailHtml;
    private String candidateRequirementHtml;
    private String benefitHtml;
    private String workingTime;
    private LocalDateTime deadline;
    private String receiverEmail;
    private String receiverPhone;
    private String receiverName;
    private String applicationMethod;
    private String contactPhone;
    private String contactEmail;
    private String insuranceBenefit;
    private String healthBenefit;
    private String bonusBenefit;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Integer department_id;

    // getter, setter cho tất cả các trường
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getWorkingType() { return workingType; }
    public void setWorkingType(String workingType) { this.workingType = workingType; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }
    public String getGenderRequirement() { return genderRequirement; }
    public void setGenderRequirement(String genderRequirement) { this.genderRequirement = genderRequirement; }
    public String getExperienceRequirement() { return experienceRequirement; }
    public void setExperienceRequirement(String experienceRequirement) { this.experienceRequirement = experienceRequirement; }
    public String getJobDetailHtml() { return jobDetailHtml; }
    public void setJobDetailHtml(String jobDetailHtml) { this.jobDetailHtml = jobDetailHtml; }
    public String getCandidateRequirementHtml() { return candidateRequirementHtml; }
    public void setCandidateRequirementHtml(String candidateRequirementHtml) { this.candidateRequirementHtml = candidateRequirementHtml; }
    public String getBenefitHtml() { return benefitHtml; }
    public void setBenefitHtml(String benefitHtml) { this.benefitHtml = benefitHtml; }
    public String getWorkingTime() { return workingTime; }
    public void setWorkingTime(String workingTime) { this.workingTime = workingTime; }
    public LocalDateTime getDeadline() { return deadline; }
    public void setDeadline(LocalDateTime deadline) { this.deadline = deadline; }
    public String getReceiverEmail() { return receiverEmail; }
    public void setReceiverEmail(String receiverEmail) { this.receiverEmail = receiverEmail; }
    public String getReceiverPhone() { return receiverPhone; }
    public void setReceiverPhone(String receiverPhone) { this.receiverPhone = receiverPhone; }
    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }
    public String getApplicationMethod() { return applicationMethod; }
    public void setApplicationMethod(String applicationMethod) { this.applicationMethod = applicationMethod; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    public String getInsuranceBenefit() { return insuranceBenefit; }
    public void setInsuranceBenefit(String insuranceBenefit) { this.insuranceBenefit = insuranceBenefit; }
    public String getHealthBenefit() { return healthBenefit; }
    public void setHealthBenefit(String healthBenefit) { this.healthBenefit = healthBenefit; }
    public String getBonusBenefit() { return bonusBenefit; }
    public void setBonusBenefit(String bonusBenefit) { this.bonusBenefit = bonusBenefit; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getModifiedAt() { return modifiedAt; }
    public void setModifiedAt(LocalDateTime modifiedAt) { this.modifiedAt = modifiedAt; }
    public Integer getDepartment_id() { return department_id; }
    public void setDepartment_id(Integer department_id) { this.department_id = department_id; }
} 