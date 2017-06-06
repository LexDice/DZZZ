<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" indent="yes" encoding="UTF-8"/>
<xsl:template match="/">
{"<xsl:value-of select="//h4[1]/text()"/>":{
"<xsl:value-of select="//tbody[1]/tr[1]/td[1]/."/>":"<xsl:value-of select="//tbody[1]/tr[1]/td[2]/span/span[@en='en']/text()"/>",
<xsl:for-each select="//tbody">
<xsl:if test="position()=1">
<xsl:for-each select="tr">
<xsl:if test="position()!=1">
"<xsl:value-of select="td[1]/."/>":"<xsl:value-of select="td[2]/."/>",</xsl:if>
</xsl:for-each>
</xsl:if>
</xsl:for-each>
<xsl:for-each select="//div[@class='block showcase gallery']//img">
<xsl:if test="position()!=last()">
"IMG-<xsl:value-of select="@src"/>":"http://www.japanparts.it<xsl:value-of select="@src"/>",</xsl:if>
<xsl:if test="position()=last()">
"IMG-<xsl:value-of select="@src"/>":"http://www.japanparts.it<xsl:value-of select="@src"/>"</xsl:if>
</xsl:for-each>
}
}
</xsl:template>
</xsl:stylesheet>