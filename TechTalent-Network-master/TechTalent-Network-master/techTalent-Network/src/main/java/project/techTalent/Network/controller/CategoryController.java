package project.techTalent.Network.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import project.techTalent.Network.payloads.ApiResponse;
import project.techTalent.Network.payloads.CategoryDto;
import project.techTalent.Network.payloads.UserDto;
import project.techTalent.Network.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController
{
	@Autowired
	private CategoryService categoryService;
     //create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory( @Valid  @RequestBody CategoryDto categoryDto)
	{
		CategoryDto categories =this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(categories , HttpStatus.CREATED);
		
	}
	//update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory( @Valid  @RequestBody CategoryDto categoryDto ,@PathVariable Integer categoryId)
	{
		CategoryDto categories = this.categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(categories , HttpStatus.OK );	
	}
	//delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId)
	{
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Categories deleted successfully" , true), HttpStatus.OK);
	}
	//get
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId)
	{
		CategoryDto category = this.categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(category , HttpStatus.OK);	
	}
	//get all
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>>  getCategories()
	{
		List<CategoryDto> categories = this.categoryService.getAllCategory();
		return  ResponseEntity.ok(categories );
	}
}
