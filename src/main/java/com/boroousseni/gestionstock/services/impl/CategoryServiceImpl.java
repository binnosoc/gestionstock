package com.boroousseni.gestionstock.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boroousseni.gestionstock.dto.CategoryDto;
import com.boroousseni.gestionstock.exceptions.EntityNotFoundException;
import com.boroousseni.gestionstock.exceptions.ErrorCode;
import com.boroousseni.gestionstock.exceptions.InvalidEntityException;
import com.boroousseni.gestionstock.exceptions.InvalidOperationException;
import com.boroousseni.gestionstock.models.Item;
import com.boroousseni.gestionstock.repository.CategoryRepository;
import com.boroousseni.gestionstock.repository.ItemRepository;
import com.boroousseni.gestionstock.services.CategoryService;
import com.boroousseni.gestionstock.validators.CategoryValidator;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;
	private ItemRepository itemRepository;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository, ItemRepository itemRepository) {
		this.categoryRepository = categoryRepository;
		this.itemRepository = itemRepository;
	}

	@Override
	public CategoryDto save(CategoryDto dto) {
		// TODO Auto-generated method stub
		List<String> errors = CategoryValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Item is not valid {}", dto);
			throw new InvalidEntityException("La category n'est pas valide", ErrorCode.CATEGORY_NOT_VALID, errors);
		}
		return CategoryDto.fromEntity(categoryRepository.save(CategoryDto.toEntity(dto)));
	}

	@Override
	public CategoryDto findById(Integer id) {
		// TODO Auto-generated method stub
		if (id == null) {
			log.error("Category ID is null");
			return null;
		}
		return categoryRepository.findById(id).map(CategoryDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucune category avec l'ID = " + id + " n' ete trouve dans la BDD",
						ErrorCode.CATEGORY_NOT_FOUND));
	}

	@Override
	public CategoryDto findByCode(String code) {
		// TODO Auto-generated method stub
		if (!StringUtils.isEmpty(code)) {
			log.error("Category CODE is null");
			return null;
		}
		return categoryRepository.findByName(code).map(CategoryDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucune category avec le CODE = " + code + " n' ete trouve dans la BDD",
						ErrorCode.CATEGORY_NOT_FOUND));
	}

	@Override
	public List<CategoryDto> findAllByCompanyId(Integer id) {
		// TODO Auto-generated method stub
		if (id == null) {
			log.error("Category ID is null");
			return null;
		}
		return categoryRepository.findAllByCompanyId(id).stream().map(CategoryDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("Category ID is null");
			return;
		}

		List<Item> items = itemRepository.findAllByCategoryId(id);

		if (!items.isEmpty()) {
			throw new InvalidOperationException("Impossible de supprimer cette categorie qui est deja utilisée",
					ErrorCode.CATEGORY_ALREADY_IN_USE);
		}

		categoryRepository.deleteById(id);
	}

}
