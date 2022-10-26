package com.ithao.pojo;

public class brand {

    private Integer id;

    private String brandName;

    private String companyName;

    private Integer ordered;

    private String description;

    private Integer status;

    public brand(Integer id, String brandName, String companyName, Integer ordered, String description, Integer status) {
        this.id = id;
        this.brandName = brandName;
        this.companyName = companyName;
        this.ordered = ordered;
        this.description = description;
        this.status = status;
    }

    @Override
    public String toString() {
        return "brand{" +
                "id=" + id +
                ", brand_name='" + brandName + '\'' +
                ", company_name='" + companyName + '\'' +
                ", ordered=" + ordered +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    public brand() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brand_name) {
        this.brandName = brand_name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String company_name) {
        this.companyName = company_name;
    }

    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
