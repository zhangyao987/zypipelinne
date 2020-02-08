package cn.com.zy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpringJpaRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
