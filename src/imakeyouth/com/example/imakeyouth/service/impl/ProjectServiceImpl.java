package com.example.imakeyouth.service.impl;

import com.example.imakeyouth.model.Project;
import com.example.imakeyouth.dao.ProjectDAO;
import com.example.imakeyouth.service.IProjectService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 虚拟项目 服务实现类
 * </p>
 *
 * @author xiaobei
 * @since 2017-09-30
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectDAO, Project> implements IProjectService {
	
}
