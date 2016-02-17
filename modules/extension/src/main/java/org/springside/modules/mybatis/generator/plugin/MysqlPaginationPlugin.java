/*
 * Copyright 2013 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package org.springside.modules.mybatis.generator.plugin;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellRunner;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.*;

import java.util.List;

/**
 * 类MysqlPaginationPlugin.java的实现描述： Mysql Mybatis Xml生成工具
 * <p>
 *
 */
public class MysqlPaginationPlugin extends PluginAdapter {

    private static final String JAVA_LANG_INTEGER = "java.lang.Integer";
	private static String FULLY_QUALIFIED_PAGE = "com.mistong.kuko.common.PageUtil";

    /**
     * generator XXExample
     */
    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    	// 查询条件类增加自定义属性
    	//addPage(topLevelClass, introspectedTable, "page")
    	addIntegerField(topLevelClass, introspectedTable, "pageOffset");
    	addIntegerField(topLevelClass, introspectedTable, "pageSize");
        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }

    /**
     * 在XXExample对象里添加Page对象属性
     * 
     * @param topLevelClass
     * @param introspectedTable
     * @param name
     */
    private void addPage(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {
        topLevelClass.addImportedType(new FullyQualifiedJavaType(FULLY_QUALIFIED_PAGE));
        CommentGenerator commentGenerator = context.getCommentGenerator();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(new FullyQualifiedJavaType(FULLY_QUALIFIED_PAGE));
        field.setName(name);
        commentGenerator.addFieldComment(field, introspectedTable);
        topLevelClass.addField(field);
        char c = name.charAt(0);
        String camel = Character.toUpperCase(c) + name.substring(1);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("set" + camel);
        method.addParameter(new Parameter(new FullyQualifiedJavaType(FULLY_QUALIFIED_PAGE), name));
        method.addBodyLine("this." + name + "=" + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType(FULLY_QUALIFIED_PAGE));
        method.setName("get" + camel);
        method.addBodyLine("return " + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
    }

    /**
     * 在XXExample对象里添加Page对象属性
     * 
     * @param topLevelClass
     * @param introspectedTable
     * @param name
     */
    private void addIntegerField(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {
        topLevelClass.addImportedType(new FullyQualifiedJavaType(JAVA_LANG_INTEGER));
        CommentGenerator commentGenerator = context.getCommentGenerator();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(new FullyQualifiedJavaType(JAVA_LANG_INTEGER));
        field.setName(name);
        commentGenerator.addFieldComment(field, introspectedTable);
        topLevelClass.addField(field);
        char c = name.charAt(0);
        String camel = Character.toUpperCase(c) + name.substring(1);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("set" + camel);
        method.addParameter(new Parameter(new FullyQualifiedJavaType(JAVA_LANG_INTEGER), name));
        method.addBodyLine("this." + name + "=" + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType(JAVA_LANG_INTEGER));
        method.setName("get" + camel);
        method.addBodyLine("return " + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
    }

    // 添删改Document的sql语句及属性
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {

        XmlElement parentElement = document.getRootElement();

        updateDocumentNameSpace(introspectedTable, parentElement);

        moveDocumentInsertSql(parentElement);

        moveDocumentUpdateByPrimaryKeySql(parentElement);

        generateMysqlPageSql(parentElement);

        generateDataAccessSql(parentElement);

        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    private void generateMysqlPageSql(XmlElement parentElement) {
        // Mysql分页语句后半部分
        XmlElement paginationSuffixElement = new XmlElement("sql");
        context.getCommentGenerator().addComment(paginationSuffixElement);
        paginationSuffixElement.addAttribute(new Attribute("id", "MysqlDialectSuffix"));
        XmlElement pageEnd = new XmlElement("if");
        pageEnd.addAttribute(new Attribute("test", "pageOffset &gt;= 0 and pageSize &gt; 0"));
        pageEnd.addElement(new TextElement("<![CDATA[ LIMIT #{pageOffset,jdbcType=INTEGER}, #{pageSize,jdbcType=INTEGER} ]]>"));
        paginationSuffixElement.addElement(pageEnd);
        parentElement.addElement(paginationSuffixElement);
    }

    private void generateDataAccessSql(XmlElement parentElement) {

        XmlElement fullOrgPathElement = new XmlElement("sql");
        context.getCommentGenerator().addComment(fullOrgPathElement);
        fullOrgPathElement.addAttribute(new Attribute("id", "fullOrgPath"));
        XmlElement pageStart = new XmlElement("if");
        pageStart.addAttribute(new Attribute("test", "fullOrgPath != null"));
        fullOrgPathElement.addElement(pageStart);
        parentElement.addElement(fullOrgPathElement);

        XmlElement ownerElement = new XmlElement("sql");
        context.getCommentGenerator().addComment(ownerElement);
        ownerElement.addAttribute(new Attribute("id", "owner"));
        XmlElement pageEnd = new XmlElement("if");
        pageEnd.addAttribute(new Attribute("test", "owner != null"));
        ownerElement.addElement(pageEnd);
        parentElement.addElement(ownerElement);
    }

    private void moveDocumentUpdateByPrimaryKeySql(XmlElement parentElement) {
        XmlElement updateElement = null;
        for (Element element : parentElement.getElements()) {
            XmlElement xmlElement = (XmlElement) element;
            if (xmlElement.getName().equals("update")) {
                for (Attribute attribute : xmlElement.getAttributes()) {
                    if (attribute.getValue().equals("updateByPrimaryKey")) {
                        updateElement = xmlElement;
                        break;
                    }
                }

            }

        }
        parentElement.getElements().remove(updateElement);
    }

    private void moveDocumentInsertSql(XmlElement parentElement) {
        XmlElement insertElement = null;
        for (Element element : parentElement.getElements()) {
            XmlElement xmlElement = (XmlElement) element;
            if (xmlElement.getName().equals("insert")) {
                for (Attribute attribute : xmlElement.getAttributes()) {
                    if (attribute.getValue().equals("insert")) {
                        insertElement = xmlElement;
                        break;
                    }
                }

            }

        }
        parentElement.getElements().remove(insertElement);
    }

    private void updateDocumentNameSpace(IntrospectedTable introspectedTable, XmlElement parentElement) {
        Attribute namespaceAttribute = null;
        for (Attribute attribute : parentElement.getAttributes()) {
            if (attribute.getName().equals("namespace")) {
                namespaceAttribute = attribute;
            }
        }
        parentElement.getAttributes().remove(namespaceAttribute);
        parentElement.getAttributes().add(new Attribute("namespace", introspectedTable.getMyBatis3JavaMapperType()));
    }

    // selectByPrimaryKey
    @Override
    public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return super.sqlMapSelectByPrimaryKeyElementGenerated(element, introspectedTable);
    }

    // updateByPrimaryKeySelective
    @Override
    public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        List<Element> elements = element.getElements();
        XmlElement setItem = null;
        int modifierItemIndex = -1;
        int gmtModifiedItemIndex = -1;
        for (Element e : elements) {
            if (e instanceof XmlElement) {
                setItem = (XmlElement) e;
                for (int i = 0; i < setItem.getElements().size(); i++) {
                    XmlElement xmlElement = (XmlElement) setItem.getElements().get(i);
                    for (Attribute att : xmlElement.getAttributes()) {
                        if (att.getValue().equals("modifier != null")) {
                            modifierItemIndex = i;
                            break;
                        }

                        if (att.getValue().equals("gmtModified != null")) {
                            gmtModifiedItemIndex = i;
                            break;
                        }

                    }
                }
            }

        }

        if (modifierItemIndex != -1 && setItem != null) {
            addXmlElementModifier(setItem, modifierItemIndex);

        }

        if (gmtModifiedItemIndex != -1 && setItem != null) {
            addGmtModifiedXmlElement(setItem, gmtModifiedItemIndex);
        }

        return super.sqlMapUpdateByPrimaryKeySelectiveElementGenerated(element, introspectedTable);
    }

    private void addGmtModifiedXmlElement(XmlElement setItem, int gmtModifiedItemIndex) {
        XmlElement defaultGmtModified = new XmlElement("if");
        defaultGmtModified.addAttribute(new Attribute("test", "gmtModified == null"));
        defaultGmtModified.addElement(new TextElement("GMT_MODIFIED = SYSDATE(),"));
        setItem.getElements().add(gmtModifiedItemIndex + 1, defaultGmtModified);
    }

    private void addXmlElementModifier(XmlElement setItem, int modifierItemIndex) {
        XmlElement defaultmodifier = new XmlElement("if");
        defaultmodifier.addAttribute(new Attribute("test", "modifier == null"));
        defaultmodifier.addElement(new TextElement("MODIFIER = 'system',"));
        setItem.getElements().add(modifierItemIndex + 1, defaultmodifier);
    }

    // updateByPrimaryKey
    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return super.sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    // deleteByPrimaryKey
    public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return super.sqlMapDeleteByPrimaryKeyElementGenerated(element, introspectedTable);
    }

    // insert
    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        List<Element> elements = element.getElements();
        XmlElement fieldItem = null;
        XmlElement valueItem = null;
        for (Element e : elements) {
            if (e instanceof XmlElement) {
                XmlElement xmlElement = (XmlElement) e;
                if (xmlElement.getName().equals("trim")) {
                    for (Attribute arr : xmlElement.getAttributes()) {
                        if (arr.getValue().equals("(")) {
                            fieldItem = xmlElement;
                            break;
                        }

                        if (arr.getValue().equals("values (")) {
                            valueItem = xmlElement;
                            break;
                        }

                    }
                }

            }

        }

        if (fieldItem != null) {
        }

        if (valueItem != null) {
        }

        return super.sqlMapInsertSelectiveElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        Parameter parameter = new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()), "record");
        method.getParameters().clear();
        method.addParameter(parameter);
        return super.clientDeleteByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable);
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        List<Method> methods = interfaze.getMethods();
        Method insertMethod = null;
        for (Method method : methods) {
            if (method.getName().equals("insert")) {
                insertMethod = method;
                break;
            }
        }
        methods.remove(insertMethod);

        Method updateMethod = null;
        for (Method method : methods) {
            if (method.getName().equals("updateByPrimaryKey")) {
                updateMethod = method;
                break;
            }
        }
        methods.remove(updateMethod);

        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    // selectByExample
    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        XmlElement lastXmlE = (XmlElement) element.getElements().remove(element.getElements().size() - 1);
        XmlElement isdeletedElement = new XmlElement("if");
        isdeletedElement.addAttribute(new Attribute("test", "oredCriteria.size != 0"));
        element.addElement(isdeletedElement);
        isdeletedElement = new XmlElement("if");
        isdeletedElement.addAttribute(new Attribute("test", "oredCriteria.size == 0"));
        element.addElement(isdeletedElement);

        XmlElement fullOrgPath = new XmlElement("include");
        fullOrgPath.addAttribute(new Attribute("refid", "fullOrgPath"));
        element.addElement(fullOrgPath);

        XmlElement owner = new XmlElement("include");
        owner.addAttribute(new Attribute("refid", "owner"));
        element.addElement(owner);

        element.addElement(lastXmlE);
        XmlElement isNotNullElement = new XmlElement("include"); //$NON-NLS-1$     
        isNotNullElement.addAttribute(new Attribute("refid", "MysqlDialectSuffix"));
        element.getElements().add(isNotNullElement);

        return super.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    // countByExample
    @Override
    public boolean sqlMapCountByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        XmlElement isNotNullElement = new XmlElement("if");
        isNotNullElement.addAttribute(new Attribute("test", "oredCriteria.size != 0"));
        element.addElement(isNotNullElement);
        isNotNullElement = new XmlElement("if");
        isNotNullElement.addAttribute(new Attribute("test", "oredCriteria.size == 0"));
        element.addElement(isNotNullElement);
        XmlElement fullOrgPath = new XmlElement("include");
        fullOrgPath.addAttribute(new Attribute("refid", "fullOrgPath"));
        element.addElement(fullOrgPath);

        XmlElement owner = new XmlElement("include");
        owner.addAttribute(new Attribute("refid", "owner"));
        element.addElement(owner);
        return super.sqlMapCountByExampleElementGenerated(element, introspectedTable);
    }

    public boolean validate(List<String> warnings) {
        return true;
    }

    public static void main(String[] args) {
        String config = MysqlPaginationPlugin.class.getClassLoader().getResource("generatorConfig.xml").getFile();
        String[] arg = { "-configfile", config, "-overwrite" };
        ShellRunner.main(arg);
    }
}
