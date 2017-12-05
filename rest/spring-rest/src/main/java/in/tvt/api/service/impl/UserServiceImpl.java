package in.tvt.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.tvt.api.entity.Usr;
import in.tvt.api.exception.BadRequestException;
import in.tvt.api.exception.NotFoundException;
import in.tvt.api.repository.UserRepository;
import in.tvt.api.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository repository;
	
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Usr> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Usr findOne(String id) {
		Usr existing = repository.findOne(id);
		if(existing == null)
		{
			throw new NotFoundException("User with id " + id + "does not exist");
		}
		return existing;
	}

	@Override
	@Transactional
	public Usr create(Usr usr) {
		Usr existing = repository.findByEmail(usr.getEmail());
		if(existing != null)
		{
			throw new BadRequestException("User with email " + usr.getEmail() + "already exists");
		}
		
		return repository.create(usr);
	}

	@Override
	@Transactional
	public Usr update(String id, Usr usr) {
		Usr existing = repository.findOne(id);
		if(existing == null)
		{
			throw new NotFoundException("User with id " + id + "does not exist");
		}
		return repository.update(usr);
	}

	@Override
	@Transactional
	public void delete(String id) {
		Usr existing = repository.findOne(id);
		if(existing == null)
		{
			throw new NotFoundException("User with id " + id + "does not exist");
		}
		repository.delete(existing);
	}

}
