package ${packageName};

import com.richcoder.common.util.vo.IdVO;
import com.richcoder.common.util.vo.Page;
import com.richcoder.common.util.vo.Pageable;
import ${config.modelPackageName}.${tableInfo.className};
import ${config.servicePackageName}.${tableInfo.className}Service;
import ${config.dtoPackageName}.request.${tableInfo.classPackageName}.${tableInfo.className}ModifyDTO;
import ${config.dtoPackageName}.request.${tableInfo.classPackageName}.${tableInfo.className}PageableDTO;
import ${config.dtoPackageName}.response.${tableInfo.classPackageName}.${tableInfo.className}DTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
* ${tableInfo.tableComment} 相关接口
*/
@Api(tags = "${tableInfo.tableComment}相关接口", description="${tableInfo.tableComment}",
consumes= MediaType.APPLICATION_JSON_UTF8_VALUE,
produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping("/${tableInfo.shortStartClassName}")
public class ${tableInfo.className}Ctrl {

private static final Logger log = LoggerFactory.getLogger(${tableInfo.className}Ctrl.class);

@Resource(name="${tableInfo.shortStartClassName}Service")
private ${tableInfo.className}Service ${tableInfo.shortStartClassName}Service;

@ApiOperation(value = "查询${tableInfo.tableComment}分页列表")
@PostMapping("/page")
public Page
<${tableInfo.className}DTO> page( @RequestBody ${tableInfo.className}PageableDTO dto){
    Page
    <${tableInfo.className}DTO> page = ${tableInfo.shortStartClassName}Service.page(dto);
        return page;
        }

        @ApiOperation(value = "根据id查询${tableInfo.tableComment}")
        @PostMapping("/get")
        public ${tableInfo.className}DTO getOne(@Validated @RequestBody IdVO dto){
        ${tableInfo.className} data = ${tableInfo.shortStartClassName}Service.getOne(dto.getId());
        if (data == null){
        return null;
        }
        ${tableInfo.className}DTO result = new ${tableInfo.className}DTO();
        BeanUtils.copyProperties(data, result);
        return result;
        }

        @ApiOperation(value = "新增${tableInfo.tableComment}")
        @PostMapping("/add")
        public Long add(@Validated(${tableInfo.className}ModifyDTO.Create.class)
        @RequestBody ${tableInfo.className}ModifyDTO dto){
        ${tableInfo.className} data = new ${tableInfo.className}();
        BeanUtils.copyProperties(dto, data);
        data = ${tableInfo.shortStartClassName}Service.save(data);
        return data.getId();
        }

        @ApiOperation(value = "更新${tableInfo.tableComment}")
        @PostMapping("/update")
        public void update(@Validated(${tableInfo.className}ModifyDTO.Update.class)
        @RequestBody ${tableInfo.className}ModifyDTO dto){
        ${tableInfo.className} data = new ${tableInfo.className}();
        BeanUtils.copyProperties(dto, data);
        ${tableInfo.shortStartClassName}Service.saveNotNull(data);
        }

        @ApiOperation(value = "删除${tableInfo.tableComment}")
        @PostMapping("/delete")
        public void delete(@Validated @RequestBody IdVO dto){
        ${tableInfo.shortStartClassName}Service.deleteById(dto.getId());
        }
        }