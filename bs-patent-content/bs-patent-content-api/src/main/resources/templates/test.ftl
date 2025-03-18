<#--<!DOCTYPE html>-->
<#--<html lang="zh-CN">-->

<#--<head>-->
<#--    <meta charset="UTF-8">-->
<#--    <title>专利信息展示</title>-->
<#--    <style>-->
<#--        /* 设置全局样式 */-->
<#--        html, body {-->
<#--            height: 100%;-->
<#--            margin: 0;-->
<#--            padding: 0;-->
<#--            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;-->
<#--            background-color: #f4f4f9;-->
<#--            color: #333;-->
<#--        }-->

<#--        /* 顶部标题样式 */-->
<#--        .header {-->
<#--            background-color: #2c3e50;-->
<#--            color: white;-->
<#--            text-align: center;-->
<#--            padding: 20px 0;-->
<#--        }-->

<#--        /* 主内容容器样式 */-->
<#--        .main-content {-->
<#--            display: flex;-->
<#--            flex-direction: column;-->
<#--            align-items: center;-->
<#--            padding: 20px;-->
<#--        }-->

<#--        /* 每个信息板块的样式 */-->
<#--        .info-section {-->
<#--            background-color: #fff;-->
<#--            border-radius: 8px;-->
<#--            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);-->
<#--            padding: 20px;-->
<#--            width: 80%;-->
<#--            max-width: 600px;-->
<#--            margin-bottom: 20px;-->
<#--        }-->

<#--        /* 信息板块标题样式 */-->
<#--        .section-title {-->
<#--            font-size: 1.2em;-->
<#--            font-weight: bold;-->
<#--            color: #2c3e50;-->
<#--            margin-bottom: 10px;-->
<#--        }-->

<#--        /* 图片样式 */-->
<#--        img {-->
<#--            max-width: 100%;-->
<#--            height: auto;-->
<#--            border-radius: 4px;-->
<#--        }-->
<#--    </style>-->
<#--</head>-->

<#--<body>-->
<#--<!-- 网页顶部标题 &ndash;&gt;-->
<#--<div class="header">-->
<#--    <#if model.courseBase.name?has_content>-->
<#--        <h1>${model.courseBase.name}</h1>-->
<#--    </#if>-->
<#--</div>-->
<#--<!-- 主内容区域 &ndash;&gt;-->
<#--<div class="main-content">-->
<#--    <!-- 分类信息板块 &ndash;&gt;-->
<#--    <#if model.courseBase.st?has_content || model.courseBase.mt?has_content>-->
<#--        <div class="info-section">-->
<#--            <div class="section-title">分类</div>-->
<#--            <div>-->
<#--                <#if model.courseBase.st?has_content>-->
<#--                    <span>${model.courseBase.stName}</span>-->
<#--                </#if>-->
<#--                <#if model.courseBase.st?has_content && model.courseBase.mt?has_content>-->
<#--                    --->
<#--                </#if>-->
<#--                <#if model.courseBase.mt?has_content>-->
<#--                    <span>${model.courseBase.mtName}</span>-->
<#--                </#if>-->
<#--            </div>-->
<#--        </div>-->
<#--    </#if>-->
<#--    <!-- 作者信息板块 &ndash;&gt;-->
<#--    <#if model.courseBase.creatPeople?has_content>-->
<#--        <div class="info-section">-->
<#--            <div class="section-title">作者</div>-->
<#--            <div>-->
<#--                <#list model.courseBase.creatPeople as author>-->
<#--                    <span>${author}</span>-->
<#--                    <#if !author_has_next>, </#if>-->
<#--                </#list>-->
<#--            </div>-->
<#--        </div>-->
<#--    </#if>-->
<#--    <!-- 专利介绍信息板块 &ndash;&gt;-->
<#--    <#if model.courseBase.description?has_content>-->
<#--        <div class="info-section">-->
<#--            <div class="section-title">介绍</div>-->
<#--            <div>-->
<#--                <span>${model.courseBase.description}</span>-->
<#--            </div>-->
<#--        </div>-->
<#--    </#if>-->
<#--    <!-- 专利图片信息板块 &ndash;&gt;-->
<#--    <#if model.courseBase.pic?has_content>-->
<#--        <div class="info-section">-->
<#--            <div class="section-title">专利图片</div>-->
<#--            <div>-->
<#--                <img src="http://file.bs.cn${model.courseBase.pic}" alt="${model.courseBase.name}">-->
<#--            </div>-->
<#--        </div>-->
<#--    </#if>-->
<#--</div>-->
<#--</body>-->

<#--</html>-->


<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${model.courseBase.name?default('未知专利名称')}</title>
    <style>
        /* 全局样式 */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: url('https://cdn.pixabay.com/photo/2017/08/30/01/05/milky-way-2695569_1280.jpg') no-repeat center center fixed;
            background-size: cover;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        /* 头部样式 */
        header {
            background-color: rgba(0, 0, 0, 0.7);
            color: white;
            text-align: center;
            padding: 20px;
        }

        header h1 {
            margin: 0;
            font-size: 32px;
        }

        /* 容器样式 */
        .container {
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
            padding: 30px;
            width: 80%;
            max-width: 800px;
            margin: 30px auto;
            flex: 1;
        }

        /* 表格样式 */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th,
        td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #e0e0e0;
        }

        th {
            background-color: #f9f9f9;
            color: #555;
            font-weight: 600;
            width: 30%; /* 设置表头列宽度为 30%，可按需调整 */
        }

        /* 摘要部分样式 */
        .abstract {
            margin-top: 30px;
            line-height: 1.6;
            border-top: 1px solid #e0e0e0;
            padding-top: 20px;
        }

        .abstract h2 {
            color: #333;
            font-size: 22px;
            margin-bottom: 10px;
        }

        /* 底部样式 */
        footer {
            background-color: rgba(0, 0, 0, 0.7);
            color: white;
            text-align: center;
            padding: 10px;
        }
    </style>
</head>

<body>
<!-- 头部区域 -->
<header>
    <h1>专利信息详情</h1>
</header>
<!-- 主体内容区域 -->
<div class="container">
    <#if model.courseBase??>
        <h1>${model.courseBase.name?default('未知专利名称')}</h1>
        <table>
            <tr>
                <th>申请号</th>
                <td>${model.courseBase.applicationNumber?default('未知申请号')}</td>
            </tr>
            <tr>
                <th>申请人</th>
                <td>${model.courseBase.applicant?default('未知申请人')}</td>
            </tr>
            <tr>
                <th>发明人</th>
                <td>${model.courseBase.inventor?default('未知发明人')}</td>
            </tr>
            <tr>
                <th>申请人所在国家</th>
                <td>${model.courseBase.country?default('未知国家')}</td>
            </tr>
            <tr>
                <th>分类</th>
                <td>${model.courseBase.classification?default('未知分类')}</td>
            </tr>
        </table>
        <div class="abstract">
            <h2>专利摘要</h2>
            <p>${model.courseBase.abstract?default('暂无专利摘要信息')}</p>
        </div>
    <#else>
        <h1>未找到专利信息</h1>
    </#if>
</div>
<!-- 底部区域 -->
<footer>
    &copy; 2025 专利信息管理系统. 保留所有权利.
</footer>
</body>

</html>