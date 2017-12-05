package in.tvt.api.repository;

import java.util.List;

import in.tvt.api.entity.Usr;

public interface UserRepository 
{
	public List<Usr> findAll();
	public Usr findOne(String id);
	public Usr findByEmail(String id);
	public Usr create(Usr user);
	public Usr update(Usr user);
	public void delete(Usr user);
}
