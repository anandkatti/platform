/**
 * Autogenerated by Thrift Compiler (0.7.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package org.apache.hadoop.hive.metastore.api;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrivilegeGrantInfo implements org.apache.thrift.TBase<PrivilegeGrantInfo, PrivilegeGrantInfo._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("PrivilegeGrantInfo");

  private static final org.apache.thrift.protocol.TField PRIVILEGE_FIELD_DESC = new org.apache.thrift.protocol.TField("privilege", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField CREATE_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("createTime", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField GRANTOR_FIELD_DESC = new org.apache.thrift.protocol.TField("grantor", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField GRANTOR_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("grantorType", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField GRANT_OPTION_FIELD_DESC = new org.apache.thrift.protocol.TField("grantOption", org.apache.thrift.protocol.TType.BOOL, (short)5);

  private String privilege; // required
  private int createTime; // required
  private String grantor; // required
  private PrincipalType grantorType; // required
  private boolean grantOption; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PRIVILEGE((short)1, "privilege"),
    CREATE_TIME((short)2, "createTime"),
    GRANTOR((short)3, "grantor"),
    /**
     * 
     * @see PrincipalType
     */
    GRANTOR_TYPE((short)4, "grantorType"),
    GRANT_OPTION((short)5, "grantOption");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // PRIVILEGE
          return PRIVILEGE;
        case 2: // CREATE_TIME
          return CREATE_TIME;
        case 3: // GRANTOR
          return GRANTOR;
        case 4: // GRANTOR_TYPE
          return GRANTOR_TYPE;
        case 5: // GRANT_OPTION
          return GRANT_OPTION;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __CREATETIME_ISSET_ID = 0;
  private static final int __GRANTOPTION_ISSET_ID = 1;
  private BitSet __isset_bit_vector = new BitSet(2);

  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PRIVILEGE, new org.apache.thrift.meta_data.FieldMetaData("privilege", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CREATE_TIME, new org.apache.thrift.meta_data.FieldMetaData("createTime", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.GRANTOR, new org.apache.thrift.meta_data.FieldMetaData("grantor", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.GRANTOR_TYPE, new org.apache.thrift.meta_data.FieldMetaData("grantorType", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, PrincipalType.class)));
    tmpMap.put(_Fields.GRANT_OPTION, new org.apache.thrift.meta_data.FieldMetaData("grantOption", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(PrivilegeGrantInfo.class, metaDataMap);
  }

  public PrivilegeGrantInfo() {
  }

  public PrivilegeGrantInfo(
    String privilege,
    int createTime,
    String grantor,
    PrincipalType grantorType,
    boolean grantOption)
  {
    this();
    this.privilege = privilege;
    this.createTime = createTime;
    setCreateTimeIsSet(true);
    this.grantor = grantor;
    this.grantorType = grantorType;
    this.grantOption = grantOption;
    setGrantOptionIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public PrivilegeGrantInfo(PrivilegeGrantInfo other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    if (other.isSetPrivilege()) {
      this.privilege = other.privilege;
    }
    this.createTime = other.createTime;
    if (other.isSetGrantor()) {
      this.grantor = other.grantor;
    }
    if (other.isSetGrantorType()) {
      this.grantorType = other.grantorType;
    }
    this.grantOption = other.grantOption;
  }

  public PrivilegeGrantInfo deepCopy() {
    return new PrivilegeGrantInfo(this);
  }

  @Override
  public void clear() {
    this.privilege = null;
    setCreateTimeIsSet(false);
    this.createTime = 0;
    this.grantor = null;
    this.grantorType = null;
    setGrantOptionIsSet(false);
    this.grantOption = false;
  }

  public String getPrivilege() {
    return this.privilege;
  }

  public void setPrivilege(String privilege) {
    this.privilege = privilege;
  }

  public void unsetPrivilege() {
    this.privilege = null;
  }

  /** Returns true if field privilege is set (has been assigned a value) and false otherwise */
  public boolean isSetPrivilege() {
    return this.privilege != null;
  }

  public void setPrivilegeIsSet(boolean value) {
    if (!value) {
      this.privilege = null;
    }
  }

  public int getCreateTime() {
    return this.createTime;
  }

  public void setCreateTime(int createTime) {
    this.createTime = createTime;
    setCreateTimeIsSet(true);
  }

  public void unsetCreateTime() {
    __isset_bit_vector.clear(__CREATETIME_ISSET_ID);
  }

  /** Returns true if field createTime is set (has been assigned a value) and false otherwise */
  public boolean isSetCreateTime() {
    return __isset_bit_vector.get(__CREATETIME_ISSET_ID);
  }

  public void setCreateTimeIsSet(boolean value) {
    __isset_bit_vector.set(__CREATETIME_ISSET_ID, value);
  }

  public String getGrantor() {
    return this.grantor;
  }

  public void setGrantor(String grantor) {
    this.grantor = grantor;
  }

  public void unsetGrantor() {
    this.grantor = null;
  }

  /** Returns true if field grantor is set (has been assigned a value) and false otherwise */
  public boolean isSetGrantor() {
    return this.grantor != null;
  }

  public void setGrantorIsSet(boolean value) {
    if (!value) {
      this.grantor = null;
    }
  }

  /**
   * 
   * @see PrincipalType
   */
  public PrincipalType getGrantorType() {
    return this.grantorType;
  }

  /**
   * 
   * @see PrincipalType
   */
  public void setGrantorType(PrincipalType grantorType) {
    this.grantorType = grantorType;
  }

  public void unsetGrantorType() {
    this.grantorType = null;
  }

  /** Returns true if field grantorType is set (has been assigned a value) and false otherwise */
  public boolean isSetGrantorType() {
    return this.grantorType != null;
  }

  public void setGrantorTypeIsSet(boolean value) {
    if (!value) {
      this.grantorType = null;
    }
  }

  public boolean isGrantOption() {
    return this.grantOption;
  }

  public void setGrantOption(boolean grantOption) {
    this.grantOption = grantOption;
    setGrantOptionIsSet(true);
  }

  public void unsetGrantOption() {
    __isset_bit_vector.clear(__GRANTOPTION_ISSET_ID);
  }

  /** Returns true if field grantOption is set (has been assigned a value) and false otherwise */
  public boolean isSetGrantOption() {
    return __isset_bit_vector.get(__GRANTOPTION_ISSET_ID);
  }

  public void setGrantOptionIsSet(boolean value) {
    __isset_bit_vector.set(__GRANTOPTION_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case PRIVILEGE:
      if (value == null) {
        unsetPrivilege();
      } else {
        setPrivilege((String)value);
      }
      break;

    case CREATE_TIME:
      if (value == null) {
        unsetCreateTime();
      } else {
        setCreateTime((Integer)value);
      }
      break;

    case GRANTOR:
      if (value == null) {
        unsetGrantor();
      } else {
        setGrantor((String)value);
      }
      break;

    case GRANTOR_TYPE:
      if (value == null) {
        unsetGrantorType();
      } else {
        setGrantorType((PrincipalType)value);
      }
      break;

    case GRANT_OPTION:
      if (value == null) {
        unsetGrantOption();
      } else {
        setGrantOption((Boolean)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case PRIVILEGE:
      return getPrivilege();

    case CREATE_TIME:
      return Integer.valueOf(getCreateTime());

    case GRANTOR:
      return getGrantor();

    case GRANTOR_TYPE:
      return getGrantorType();

    case GRANT_OPTION:
      return Boolean.valueOf(isGrantOption());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case PRIVILEGE:
      return isSetPrivilege();
    case CREATE_TIME:
      return isSetCreateTime();
    case GRANTOR:
      return isSetGrantor();
    case GRANTOR_TYPE:
      return isSetGrantorType();
    case GRANT_OPTION:
      return isSetGrantOption();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof PrivilegeGrantInfo)
      return this.equals((PrivilegeGrantInfo)that);
    return false;
  }

  public boolean equals(PrivilegeGrantInfo that) {
    if (that == null)
      return false;

    boolean this_present_privilege = true && this.isSetPrivilege();
    boolean that_present_privilege = true && that.isSetPrivilege();
    if (this_present_privilege || that_present_privilege) {
      if (!(this_present_privilege && that_present_privilege))
        return false;
      if (!this.privilege.equals(that.privilege))
        return false;
    }

    boolean this_present_createTime = true;
    boolean that_present_createTime = true;
    if (this_present_createTime || that_present_createTime) {
      if (!(this_present_createTime && that_present_createTime))
        return false;
      if (this.createTime != that.createTime)
        return false;
    }

    boolean this_present_grantor = true && this.isSetGrantor();
    boolean that_present_grantor = true && that.isSetGrantor();
    if (this_present_grantor || that_present_grantor) {
      if (!(this_present_grantor && that_present_grantor))
        return false;
      if (!this.grantor.equals(that.grantor))
        return false;
    }

    boolean this_present_grantorType = true && this.isSetGrantorType();
    boolean that_present_grantorType = true && that.isSetGrantorType();
    if (this_present_grantorType || that_present_grantorType) {
      if (!(this_present_grantorType && that_present_grantorType))
        return false;
      if (!this.grantorType.equals(that.grantorType))
        return false;
    }

    boolean this_present_grantOption = true;
    boolean that_present_grantOption = true;
    if (this_present_grantOption || that_present_grantOption) {
      if (!(this_present_grantOption && that_present_grantOption))
        return false;
      if (this.grantOption != that.grantOption)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(PrivilegeGrantInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    PrivilegeGrantInfo typedOther = (PrivilegeGrantInfo)other;

    lastComparison = Boolean.valueOf(isSetPrivilege()).compareTo(typedOther.isSetPrivilege());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPrivilege()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.privilege, typedOther.privilege);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCreateTime()).compareTo(typedOther.isSetCreateTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCreateTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.createTime, typedOther.createTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetGrantor()).compareTo(typedOther.isSetGrantor());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGrantor()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.grantor, typedOther.grantor);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetGrantorType()).compareTo(typedOther.isSetGrantorType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGrantorType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.grantorType, typedOther.grantorType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetGrantOption()).compareTo(typedOther.isSetGrantOption());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGrantOption()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.grantOption, typedOther.grantOption);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    org.apache.thrift.protocol.TField field;
    iprot.readStructBegin();
    while (true)
    {
      field = iprot.readFieldBegin();
      if (field.type == org.apache.thrift.protocol.TType.STOP) { 
        break;
      }
      switch (field.id) {
        case 1: // PRIVILEGE
          if (field.type == org.apache.thrift.protocol.TType.STRING) {
            this.privilege = iprot.readString();
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 2: // CREATE_TIME
          if (field.type == org.apache.thrift.protocol.TType.I32) {
            this.createTime = iprot.readI32();
            setCreateTimeIsSet(true);
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 3: // GRANTOR
          if (field.type == org.apache.thrift.protocol.TType.STRING) {
            this.grantor = iprot.readString();
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 4: // GRANTOR_TYPE
          if (field.type == org.apache.thrift.protocol.TType.I32) {
            this.grantorType = PrincipalType.findByValue(iprot.readI32());
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 5: // GRANT_OPTION
          if (field.type == org.apache.thrift.protocol.TType.BOOL) {
            this.grantOption = iprot.readBool();
            setGrantOptionIsSet(true);
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        default:
          org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();
    validate();
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    validate();

    oprot.writeStructBegin(STRUCT_DESC);
    if (this.privilege != null) {
      oprot.writeFieldBegin(PRIVILEGE_FIELD_DESC);
      oprot.writeString(this.privilege);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldBegin(CREATE_TIME_FIELD_DESC);
    oprot.writeI32(this.createTime);
    oprot.writeFieldEnd();
    if (this.grantor != null) {
      oprot.writeFieldBegin(GRANTOR_FIELD_DESC);
      oprot.writeString(this.grantor);
      oprot.writeFieldEnd();
    }
    if (this.grantorType != null) {
      oprot.writeFieldBegin(GRANTOR_TYPE_FIELD_DESC);
      oprot.writeI32(this.grantorType.getValue());
      oprot.writeFieldEnd();
    }
    oprot.writeFieldBegin(GRANT_OPTION_FIELD_DESC);
    oprot.writeBool(this.grantOption);
    oprot.writeFieldEnd();
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("PrivilegeGrantInfo(");
    boolean first = true;

    sb.append("privilege:");
    if (this.privilege == null) {
      sb.append("null");
    } else {
      sb.append(this.privilege);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("createTime:");
    sb.append(this.createTime);
    first = false;
    if (!first) sb.append(", ");
    sb.append("grantor:");
    if (this.grantor == null) {
      sb.append("null");
    } else {
      sb.append(this.grantor);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("grantorType:");
    if (this.grantorType == null) {
      sb.append("null");
    } else {
      sb.append(this.grantorType);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("grantOption:");
    sb.append(this.grantOption);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bit_vector = new BitSet(1);
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

}
