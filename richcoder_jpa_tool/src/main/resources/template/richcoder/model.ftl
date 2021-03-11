package ${packageName};

import javax.persistence.*;

<#list tableInfo.columnTypes as item>
    import ${item};
</#list>

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "${tableInfo.tableComment}")
@Entity
@Table(name="${tableInfo.tableName}")
public class ${tableInfo.className}{

<#list tableInfo.columnInfos as item>
    <#if  item.field == tableInfo.primaryKey >
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
    </#if>
    <#if  item.attrShortType == "Date" >
        @ApiModelProperty(value="${item.comment}", example = "2020-01-01 00:00:00")
    <#else>
        @ApiModelProperty("${item.comment}")
    </#if>
    @Column(name="${item.field}")
    <#if  item.field == "id" || item.field ?ends_with("Id")>
        private Long ${item.attrName};
    <#else>
        private ${item.attrShortType} ${item.attrName};
    </#if>

</#list>
<#--
    <#list list as item>
      
    <#if  item.colName="id">  
    @Id    
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    <#elseif item.colName!="id"&&item.colLong="">  
    @Column(name="${item.colName}",nullable = ${item.colNull} )    
    <#else>  
    @Column(name="${item.colName}",length=${item.colLong},nullable = ${item.colNull})    
    </#if>  
    public ${item.colType} get${item.colUPName}() {  
        return ${item.colName};  
    }  
    public void set${item.colUPName}(${item.colType} ${item.colName}) {  
        this.${item.colName} = ${item.colName};  
    }  
    </#list>   
-->

}