/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.shardingui.web.controller;

import io.shardingsphere.shardingui.common.dto.InstanceDTO;
import io.shardingsphere.shardingui.common.dto.SlaveDataSourceDTO;
import io.shardingsphere.shardingui.servcie.OrchestrationService;
import io.shardingsphere.shardingui.web.response.ResponseResult;
import io.shardingsphere.shardingui.web.response.ResponseResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * RESTful API of orchestration operation.
 *
 * @author chenqingyang
 */
@RestController
@RequestMapping("/api/orchestration")
public final class OrchestrationController {
    
    @Autowired
    private OrchestrationService orchestrationService;
    
    /**
     * Load all instances.
     *
     * @return response result
     */
    @RequestMapping(value = "/instance", method = RequestMethod.GET)
    public ResponseResult<Collection<InstanceDTO>> loadAllInstances() {
        return ResponseResultUtil.build(orchestrationService.getALLInstance());
    }
    
    /**
     * update instance status.
     *
     * @param instanceDTO instance DTO
     * @return response result
     */
    @RequestMapping(value = "/instance", method = RequestMethod.PUT)
    public ResponseResult updateInstanceStatus(@RequestBody final InstanceDTO instanceDTO) {
        orchestrationService.updateInstanceStatus(instanceDTO.getInstanceId(), instanceDTO.isEnabled());
        return ResponseResultUtil.success();
    }
    
    /**
     * Load all slave data sources.
     *
     * @return response result
     */
    @RequestMapping(value = "/datasource", method = RequestMethod.GET)
    public ResponseResult<Collection<SlaveDataSourceDTO>> loadAllSlaveDataSources() {
        return ResponseResultUtil.build(orchestrationService.getAllSlaveDataSource());
    }
    
    /**
     * Update slave data source status.
     *
     * @param slaveDataSourceDTO slave data source DTO
     * @return response result
     */
    @RequestMapping(value = "/datasource", method = RequestMethod.PUT)
    public ResponseResult updateSlaveDataSourceStatus(@RequestBody final SlaveDataSourceDTO slaveDataSourceDTO) {
        orchestrationService.updateSlaveDataSourceStatus(slaveDataSourceDTO.getSchema(), slaveDataSourceDTO.getSlaveDataSourceName(), slaveDataSourceDTO.isEnabled());
        return ResponseResultUtil.success();
    }
    
}
