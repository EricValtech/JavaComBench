// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: simplemodel.prot

package com.vidal.sandbox.statelessvxp.pojo.protbufgen;

public final class SimplemodelProt {
  private SimplemodelProt() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface SimpleModelOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required string name = 1;
    /**
     * <code>required string name = 1;</code>
     */
    boolean hasName();
    /**
     * <code>required string name = 1;</code>
     */
    java.lang.String getName();
    /**
     * <code>required string name = 1;</code>
     */
    com.google.protobuf.ByteString
        getNameBytes();

    // required double version = 2;
    /**
     * <code>required double version = 2;</code>
     */
    boolean hasVersion();
    /**
     * <code>required double version = 2;</code>
     */
    double getVersion();
  }
  /**
   * Protobuf type {@code com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimpleModel}
   */
  public static final class SimpleModel extends
      com.google.protobuf.GeneratedMessage
      implements SimpleModelOrBuilder {
    // Use SimpleModel.newBuilder() to construct.
    private SimpleModel(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private SimpleModel(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final SimpleModel defaultInstance;
    public static SimpleModel getDefaultInstance() {
      return defaultInstance;
    }

    public SimpleModel getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private SimpleModel(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              bitField0_ |= 0x00000001;
              name_ = input.readBytes();
              break;
            }
            case 17: {
              bitField0_ |= 0x00000002;
              version_ = input.readDouble();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.internal_static_com_vidal_sandbox_statelessvxp_pojo_protbufgen_SimpleModel_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.internal_static_com_vidal_sandbox_statelessvxp_pojo_protbufgen_SimpleModel_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel.class, com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel.Builder.class);
    }

    public static com.google.protobuf.Parser<SimpleModel> PARSER =
        new com.google.protobuf.AbstractParser<SimpleModel>() {
      public SimpleModel parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new SimpleModel(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<SimpleModel> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required string name = 1;
    public static final int NAME_FIELD_NUMBER = 1;
    private java.lang.Object name_;
    /**
     * <code>required string name = 1;</code>
     */
    public boolean hasName() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string name = 1;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          name_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string name = 1;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // required double version = 2;
    public static final int VERSION_FIELD_NUMBER = 2;
    private double version_;
    /**
     * <code>required double version = 2;</code>
     */
    public boolean hasVersion() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required double version = 2;</code>
     */
    public double getVersion() {
      return version_;
    }

    private void initFields() {
      name_ = "";
      version_ = 0D;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasName()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasVersion()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getNameBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeDouble(2, version_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, getNameBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeDoubleSize(2, version_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimpleModel}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModelOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.internal_static_com_vidal_sandbox_statelessvxp_pojo_protbufgen_SimpleModel_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.internal_static_com_vidal_sandbox_statelessvxp_pojo_protbufgen_SimpleModel_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel.class, com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel.Builder.class);
      }

      // Construct using com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        name_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        version_ = 0D;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.internal_static_com_vidal_sandbox_statelessvxp_pojo_protbufgen_SimpleModel_descriptor;
      }

      public com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel getDefaultInstanceForType() {
        return com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel.getDefaultInstance();
      }

      public com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel build() {
        com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel buildPartial() {
        com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel result = new com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.name_ = name_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.version_ = version_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel) {
          return mergeFrom((com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel other) {
        if (other == com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel.getDefaultInstance()) return this;
        if (other.hasName()) {
          bitField0_ |= 0x00000001;
          name_ = other.name_;
          onChanged();
        }
        if (other.hasVersion()) {
          setVersion(other.getVersion());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasName()) {
          
          return false;
        }
        if (!hasVersion()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt.SimpleModel) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required string name = 1;
      private java.lang.Object name_ = "";
      /**
       * <code>required string name = 1;</code>
       */
      public boolean hasName() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required string name = 1;</code>
       */
      public java.lang.String getName() {
        java.lang.Object ref = name_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          name_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string name = 1;</code>
       */
      public com.google.protobuf.ByteString
          getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          name_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string name = 1;</code>
       */
      public Builder setName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        name_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string name = 1;</code>
       */
      public Builder clearName() {
        bitField0_ = (bitField0_ & ~0x00000001);
        name_ = getDefaultInstance().getName();
        onChanged();
        return this;
      }
      /**
       * <code>required string name = 1;</code>
       */
      public Builder setNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        name_ = value;
        onChanged();
        return this;
      }

      // required double version = 2;
      private double version_ ;
      /**
       * <code>required double version = 2;</code>
       */
      public boolean hasVersion() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required double version = 2;</code>
       */
      public double getVersion() {
        return version_;
      }
      /**
       * <code>required double version = 2;</code>
       */
      public Builder setVersion(double value) {
        bitField0_ |= 0x00000002;
        version_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required double version = 2;</code>
       */
      public Builder clearVersion() {
        bitField0_ = (bitField0_ & ~0x00000002);
        version_ = 0D;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimpleModel)
    }

    static {
      defaultInstance = new SimpleModel(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimpleModel)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_com_vidal_sandbox_statelessvxp_pojo_protbufgen_SimpleModel_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_vidal_sandbox_statelessvxp_pojo_protbufgen_SimpleModel_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020simplemodel.prot\022.com.vidal.sandbox.st" +
      "atelessvxp.pojo.protbufgen\",\n\013SimpleMode" +
      "l\022\014\n\004name\030\001 \002(\t\022\017\n\007version\030\002 \002(\001"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_com_vidal_sandbox_statelessvxp_pojo_protbufgen_SimpleModel_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_com_vidal_sandbox_statelessvxp_pojo_protbufgen_SimpleModel_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_com_vidal_sandbox_statelessvxp_pojo_protbufgen_SimpleModel_descriptor,
              new java.lang.String[] { "Name", "Version", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
