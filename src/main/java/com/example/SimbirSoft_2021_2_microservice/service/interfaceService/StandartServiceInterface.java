package com.example.SimbirSoft_2021_2_microservice.service.interfaceService;

public interface StandartServiceInterface<T> {
    public T registration(T o) throws Exception;
    public <S> S getAll() throws Exception;
    public <S> S getOne(Long id) throws Exception;
    public <S> S deleteOne(Long id) throws Exception;
    public T updateOne(Long id, T o) throws Exception;
}
