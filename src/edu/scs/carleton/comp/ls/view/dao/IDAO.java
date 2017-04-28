package edu.scs.carleton.comp.ls.view.dao;

public abstract interface IDAO
{
  public abstract boolean create(Object paramObject);
  
  public abstract boolean update(Object paramObject);
  
  public abstract boolean delete(int paramInt);
  
  public abstract boolean delete(String paramString);
  
  public abstract boolean delete(Object paramObject);
  
  public abstract boolean deleteAll();

  public abstract Object findByPrimaryKey(int paramInt);
  
  public abstract Object findByPrimaryKey(String paramString);
  
  public abstract Object find(Object paramObject);
  
  public abstract int getCount();
}
