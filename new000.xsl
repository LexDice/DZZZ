<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" indent="yes" encoding="UTF-8"/>
<xsl:template match="/">
{
"a":[<xsl:for-each select="//a">
"<xsl:value-of select="@href"/>",
</xsl:for-each>]
}
</xsl:template></xsl:stylesheet>