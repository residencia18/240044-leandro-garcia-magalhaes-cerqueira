package br.com.techie.shoppingstore.AP003.service;

import br.com.techie.shoppingstore.AP003.dto.form.CategoryFORM;
import br.com.techie.shoppingstore.AP003.dto.form.CategoryUpdateFORM;
import br.com.techie.shoppingstore.AP003.dto.view.CategoryVIEW;
import br.com.techie.shoppingstore.AP003.infra.exception.ResourceNotFoundException;
import br.com.techie.shoppingstore.AP003.mapper.forms.CategoryFormMapper;
import br.com.techie.shoppingstore.AP003.mapper.updates.CategoryUpdateMapper;
import br.com.techie.shoppingstore.AP003.mapper.views.CategoryViewMapper;
import br.com.techie.shoppingstore.AP003.model.Category;
import br.com.techie.shoppingstore.AP003.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private CategoryFormMapper categoryFormMapper;

  @Autowired
  private CategoryViewMapper categoryViewMapper;

  @Autowired
  private CategoryUpdateMapper categoryUpdateMapper;

  @Cacheable("categories")
  @Transactional(readOnly = true)
  public Page<CategoryVIEW> findAllPaged(Pageable pageable) {
    return categoryRepository.findAll(pageable).map(x -> categoryViewMapper.map(x));
  }

  @Transactional(readOnly = true)
  public CategoryVIEW findById(Long id) {
    Optional<Category> obj = categoryRepository.findById(id);
    Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    return categoryViewMapper.map(entity);
  }

  @Transactional
  @CacheEvict(cacheNames = {"categories"}, allEntries = true)
	public CategoryVIEW insert(CategoryFORM dto) {
		Category entity = categoryFormMapper.map(dto);
		categoryRepository.save(entity);
		return categoryViewMapper.map(entity);
	}

  @Transactional
  @CacheEvict(cacheNames = {"categories"}, allEntries = true)
	public CategoryVIEW update(CategoryUpdateFORM dto) {
	  Category entity = categoryRepository.findById(dto.category_id()).orElseThrow(() -> new EntityNotFoundException("Category not found!"));
	  entity = categoryUpdateMapper.map(dto, entity);
	  categoryRepository.save(entity);
	  return categoryViewMapper.map(entity);
	}

    @Transactional
    @CacheEvict(cacheNames = {"categories"}, allEntries = true)
	public void delete(Long id) {
	  categoryRepository.deleteById(id);
	}
}
