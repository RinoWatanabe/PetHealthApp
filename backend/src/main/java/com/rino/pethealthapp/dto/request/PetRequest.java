package com.rino.pethealthapp.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PetRequest {

    // ペット名
    @NotBlank(message = "名前を入力してください。")
    @Size(max = 20, message = "名前は20文字以内で入力してください。")
    private String petName;

    // 種別
    @NotBlank(message = "種別を選択してください。")
    private String petType;

    // 種別（その他）
    @Size(max = 20, message = "種別は20文字以内で入力してください。")
    private String customPetType;

    // 年齢
    @Min(value = 0, message = "0～100までの数字で入力してください。")
    @Max(value = 100, message = "0～100までの数字で入力してください。")
    private Integer age;

    // 性別
    private String gender;

    // 誕生日
    private LocalDate birthday;

    // ====================================
    // 引数なしコンストラクタ
    public PetRequest() {
    }

    // getter / setter
    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getCustomPetType() {
        return customPetType;
    }

    public void setCustomPetType(String customPetType) {
        this.customPetType = customPetType;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

}
