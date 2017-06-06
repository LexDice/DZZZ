<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" indent="yes" encoding="UTF-8"/>
<xsl:template match="/">
{
"a":[<xsl:for-each select="//a[@href!='#']">
<xsl:if test="text()!=''">
<xsl:if test="text()!= '&#x000A;'">
<xsl:if test="position()!=last()">
<xsl:choose>
<xsl:when test="contains(text(),'&#x000A;')">
"<xsl:value-of select="normalize-space()" disable-output-escaping="yes"/>",</xsl:when>
<xsl:otherwise>
"<xsl:value-of select="text()" disable-output-escaping="yes" />",</xsl:otherwise>
</xsl:choose>
</xsl:if>
<xsl:if test="position()=last()">
<xsl:choose>
<xsl:when test="contains(text(),'&#x000A;')">
"<xsl:value-of select="normalize-space()" disable-output-escaping="yes"/>",</xsl:when>
<xsl:otherwise>
"<xsl:value-of select="text()" disable-output-escaping="yes" />"</xsl:otherwise>
</xsl:choose>
</xsl:if>
</xsl:if>
</xsl:if>
</xsl:for-each>]
}
</xsl:template></xsl:stylesheet>