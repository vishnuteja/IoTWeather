package in.tvt.api.service;

import java.util.List;

import in.tvt.api.entity.Usr;

public interface UserService {

	public List<Usr> findAll();
	public Usr findOne(String id);
	public Usr create(Usr user);
	public Usr update(String id, Usr user);
	public void delete(String id);
}
