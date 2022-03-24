<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE xsd:schema [
<!-- Patterns -->
<!ENTITY Identifier   "(\p{L}|_|$)(\p{N}|\p{L}|_|$)*">
<!ENTITY TypeName     "&Identifier;(\.&Identifier;)*">
<!ENTITY WS       "\s*">
<!ENTITY Import     "&TypeName;(\.\*)?">
<!ENTITY ImportList   "&Import;(&WS;,&WS;&Import;)*">
<!ENTITY SetProp    "(&Identifier;|\*)">
<!ENTITY RelativeURL  "[^:#/\?]*(:{0,0}|[#/\?].*)">
<!ENTITY Length     "[0-9]*&#x25;?">
<!ENTITY AsciiName    "[A-Za-z0-9_-]*">
<!ENTITY ValidContentType  "&AsciiName;/&AsciiName;(;&WS;(charset=)?&AsciiName;)?">
<!ENTITY ValidPageEncoding  "&AsciiName;/&AsciiName;">
<!ENTITY Buffer     "[0-9]+kb">
<!ENTITY RTexpr     "&#x25;=.*&#x25;">
]>

<xsd:schema xmlns = "http://java.sun.com/JSP/Page"
            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
            targetNamespace="http://java.sun.com/JSP/Page"
            elementFormDefault="qualified"
            attributeFormDefault="unqualified"
            version="2.0">

    <xsd:simpleType name = "TypeName">
        <xsd:annotation>
            <xsd:documentation>
                TypeName is one or more Java identifiers separated by dots
                with no whitespace.
            </xsd:documentation>
        </xsd:annotation>
        <xsd:restriction base = "xsd:string">
            <xsd:pattern value = "&TypeName;"/>
        </xsd:restriction>
    </xsd:simpleType>

    <xsd:simpleType name = "ImportList">
        <xsd:annotation>
            <xsd:documentation>
                ImportList is one or more typeNames separated by commas.
                Whitespace is allowed before and after the comma.
            </xsd:documentation>
        </xsd:annotation>
        <xsd:restriction base = "xsd:string">
            <xsd:pattern value = "&ImportList;"/>
        </xsd:restriction>
    </xsd:simpleType>

    <xsd:simpleType name = "ContentType">
        <xsd:annotation>
            <xsd:documentation>
                Content Type for this page
            </xsd:documentation>
        </xsd:annotation>
        <xsd:restriction base = "xsd:string">
            <xsd:pattern value = "&ValidContentType;"/>
        </xsd:restriction>
    </xsd:simpleType>

    <xsd:simpleType name = "PageEncoding">
        <xsd:annotation>
            <xsd:documentation>
                Page Encoding for this page.  Default is that of ContentType.
            </xsd:documentation>
        </xsd:annotation>
        <xsd:restriction base = "xsd:string">
            <xsd:pattern value = "&ValidPageEncoding;"/>
        </xsd:restriction>
    </xsd:simpleType>

    <xsd:simpleType name = "ExplicitBufferSize">
        <xsd:annotation>
            <xsd:documentation>
                Buffer Size with an explicit value
            </xsd:documentation>
        </xsd:annotation>
        <xsd:restriction base = "xsd:string">
            <xsd:pattern value = "&Buffer;"/>
        </xsd:restriction>
    </xsd:simpleType>

    <xsd:simpleType name = "NoneBufferSize">
        <xsd:annotation>
            <xsd:documentation>
                Buffer Size with a "none" value
            </xsd:documentation>
        </xsd:annotation>
        <xsd:restriction base = "xsd:string">
            <xsd:enumeration value = "none"/>
        </xsd:restriction>
    </xsd:simpleType>

    <xsd:simpleType name = "BufferSize">
        <xsd:annotation>
            <xsd:documentation>
                Buffer size is xkb or none.
            </xsd:documentation>
        </xsd:annotation>
        <xsd:union memberTypes = "ExplicitBufferSize NoneBufferSize"/>
    </xsd:simpleType>

    <xsd:simpleType name = "Bool">
        <xsd:annotation>
            <xsd:documentation>
                Bool would be boolean except it does not accept 1 and 0.
            </xsd:documentation>
        </xsd:annotation>
        <xsd:restriction base = "xsd:NMTOKEN" >
            <xsd:enumeration value = "true"/>
            <xsd:enumeration value = "false"/>
        </xsd:restriction>
    </xsd:simpleType>

    <xsd:simpleType name = "RelativeURL">
        <xsd:annotation>
            <xsd:documentation>
                RelativeURL is a uriReference with no colon character
                before the first /, ? or #, if any (RFC2396).
            </xsd:documentation>
        </xsd:annotation>
        <xsd:restriction base = "xsd:anyURI">
            <xsd:pattern value = "&RelativeURL;"/>
        </xsd:restriction>
    </xsd:simpleType>

    <xsd:simpleType name = "TagFileBodyContent">
        <xsd:annotation>
            <xsd:documentation>
                Enum for different tag file body content type.
            </xsd:documentation>
        </xsd:annotation>
        <xsd:restriction base = "xsd:NMTOKEN" >
            <xsd:enumeration value = "empty"/>
            <xsd:enumeration value = "tagdependent"/>
            <xsd:enumeration value = "scriptless"/>
        </xsd:restriction>
    </xsd:simpleType>

    <xsd:simpleType name = "VariableDeclareType">
        <xsd:annotation>
            <xsd:documentation>
                Enum for different variable declaration types.
            </xsd:documentation>
        </xsd:annotation>
        <xsd:restriction base = "xsd:NMTOKEN" >
            <xsd:enumeration value = "AT_BEGIN"/>
            <xsd:enumeration value = "AT_END"/>
            <xsd:enumeration value = "NESTED"/>
        </xsd:restriction>
    </xsd:simpleType>

    <xsd:element name = "page">
        <xsd:complexType>
            <xsd:attribute name = "language" default = "java" type = "xsd:string"/>
            <xsd:attribute name = "extends" type = "TypeName"/>
            <xsd:attribute name = "contentType" default = "text/html; ISO-8859-1" type = "ContentType"/>
            <xsd:attribute name = "pageEncoding" use = "optional" type = "PageEncoding"/>
            <xsd:attribute name = "import" type = "ImportList"/>
            <xsd:attribute name = "session" default = "true" type = "Bool"/>
            <xsd:attribute name = "isELIgnored" default = "true" type = "Bool"/>
            <xsd:attribute name = "buffer" default = "8kb" type = "BufferSize"/>
            <xsd:attribute name = "autoFlush" default = "true" type = "Bool"/>
            <xsd:attribute name = "isThreadSafe" default = "true" type = "Bool"/>
            <xsd:attribute name = "info" type = "xsd:string"/>
            <xsd:attribute name = "errorPage" type = "RelativeURL"/>
            <xsd:attribute name = "isErrorPage" default = "false" type = "Bool"/>

            <!-- @since JSP 2.1 -->
            <xsd:attribute name = "deferredSyntaxAllowedAsLiteral" use = "optional" type = "Bool"/>
            <!-- @since JSP 2.1 -->
            <xsd:attribute name = "trimDirectiveWhitespaces" use = "optional" type = "Bool"/>
        </xsd:complexType>
    </xsd:element>

    <xsd:element name = "include">
        <xsd:complexType>
            <xsd:attribute name = "file" use = "required" type = "RelativeURL"/>
        </xsd:complexType>
    </xsd:element>

    <xsd:element name = "taglib">
        <xsd:complexType>
            <xsd:attribute name = "uri" use = "optional" type = "xsd:anyURI"/>
            <xsd:attribute name = "tagdir" use = "optional" type = "RelativeURL"/>
            <xsd:attribute name = "prefix" use = "required" type = "xsd:string"/>
        </xsd:complexType>
    </xsd:element>

    <xsd:element name = "tag">
        <xsd:annotation>
            <xsd:documentation>
                The tag directive is similar to the page directive, but applies to tag files instead
                of JSPs.
            </xsd:documentation>
        </xsd:annotation>
        <xsd:complexType>
            <xsd:attribute name = "display-name" use = "optional" type = "xsd:string"/>
            <xsd:attribute name = "body-content" use = "optional" type = "TagFileBodyContent"/>
            <xsd:attribute name = "dynamic-attributes" use = "optional" type = "xsd:string"/>

            <xsd:attribute name = "small-icon" use = "optional" type = "xsd:string"/>
            <xsd:attribute name = "large-icon" use = "optional" type = "xsd:string"/>
            <xsd:attribute name = "description" use = "optional" type = "xsd:string"/>
            <xsd:attribute name = "example" use = "optional" type = "xsd:string"/>

            <xsd:attribute name = "language" use = "optional" type = "xsd:string"/>
            <xsd:attribute name = "import" use = "optional" type = "xsd:string"/>
            <xsd:attribute name = "pageEncoding" use = "optional" type = "xsd:string"/>
            <xsd:attribute name = "isELIgnored" use = "optional" type = "Bool"/>

            <!-- @since JSP 2.1 -->
            <xsd:attribute name = "deferredSyntaxAllowedAsLiteral" use = "optional" type = "Bool"/>
            <!-- @since JSP 2.1 -->
            <xsd:attribute name = "trimDirectiveWhitespaces" use = "optional" type = "Bool"/>
        </xsd:complexType>
    </xsd:element>

    <xsd:element name = "attribute">
        <xsd:annotation>
            <xsd:documentation>
                The attribute directive is analogous to the &gt;b&lt;attribute&gt;/b&lt; element in the Tag Library
                Descriptor, and allows for the declaration of custom action attributes.
            </xsd:documentation>
        </xsd:annotation>
        <xsd:complexType>
            <xsd:attribute name = "name" use = "required" type = "RelativeURL"/>
            <xsd:attribute name = "required" use = "optional" type = "Bool"/>
            <xsd:attribute name = "fragment" use = "optional" type = "Bool"/>

            <xsd:attribute name = "rtexprvalue" use = "optional" type = "Bool"/>
            <xsd:attribute name = "type" use = "optional" type = "xsd:string"/>
            <xsd:attribute name = "description" use = "optional" type = "xsd:string"/>

            <!-- @since 2.1 -->
            <xsd:attribute name = "deferredValue" use = "optional" type = "Bool"/>

            <!-- @since 2.1 -->
            <xsd:attribute name = "deferredValueType" use = "optional" type = "TypeName"/>

            <!-- @since 2.1 -->
            <xsd:attribute name = "deferredMethod" use = "optional" type = "Bool"/>

            <!-- @since 2.1 -->
            <xsd:attribute name = "deferredMethodSignature" use = "optional" type = "xsd:string"/>
        </xsd:complexType>
    </xsd:element>

    <xsd:element name = "variable">
        <xsd:annotation>
            <xsd:documentation>
                directive.include is the "include directive".
                This element does not appear on XML views of JSP pages.
            </xsd:documentation>
        </xsd:annotation>
        <xsd:complexType>
            <xsd:attribute name = "name-given" use = "optional" type = "xsd:string"/>
            <xsd:attribute name = "name-from-attribute" use = "optional" type = "xsd:string"/>
            <xsd:attribute name = "alias" use = "optional" type = "xsd:string"/>

            <xsd:attribute name = "variable-class" use = "optional" type = "xsd:string"/>
            <xsd:attribute name = "declare" use = "optional" type = "Bool"/>
            <xsd:attribute name = "scope" use = "optional" type = "VariableDeclareType"/>
            <xsd:attribute name = "description" use = "optional" type = "xsd:string"/>
        </xsd:complexType>
    </xsd:element>
</xsd:schema>