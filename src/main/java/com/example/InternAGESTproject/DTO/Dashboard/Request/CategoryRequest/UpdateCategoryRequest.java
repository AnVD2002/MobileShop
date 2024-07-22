package com.example.InternAGESTproject.DTO.Dashboard.Request.CategoryRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryRequest {
    private Long categoryID;
    private String name;
    private Boolean status;
}
