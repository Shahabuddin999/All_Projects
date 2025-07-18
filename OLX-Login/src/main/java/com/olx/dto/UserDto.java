//package com.olx.dto;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//
//@ApiModel(value="User DTO")
//public class UserDto {
//	@ApiModelProperty(value="User Identifier")
//	private int id;
//	@ApiModelProperty(value="User User Name")
//	private String username;
//	@ApiModelProperty(value="User Password")
//	private String password;
//	@ApiModelProperty(value="User Roles")
//	private String roles;
//	@ApiModelProperty(value="User Active")
//	private boolean active;
//	@ApiModelProperty(value="User First Name")
//	private String firstName;
//	@ApiModelProperty(value="User Last Name")
//	private String lastName;
//	@ApiModelProperty(value="User Email")
//	private String email;
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public String getRoles() {
//		return roles;
//	}
//	public void setRoles(String roles) {
//		this.roles = roles;
//	}
//	public boolean isActive() {
//		return active;
//	}
//	public void setActive(boolean active) {
//		this.active = active;
//	}
//	public String getFirstName() {
//		return firstName;
//	}
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//	public String getLastName() {
//		return lastName;
//	}
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public UserDto(int id, String username, String password, String roles, boolean active, String firstName,
//			String lastName, String email) {
//		super();
//		this.id = id;
//		this.username = username;
//		this.password = password;
//		this.roles = roles;
//		this.active = active;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;
//	}
//	public UserDto() {
//		super();
//	}
//	@Override
//	public String toString() {
//		return "UserDto [id=" + id + ", username=" + username + ", password=" + password + ", roles=" + roles
//				+ ", active=" + active + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
//				+ "]";
//	}
//	@Override
//	public boolean equals(Object obj) {
//		UserDto userDto = (UserDto) obj;
//		if (this.username == null) {
//			return false;
//		}
//		if (this.username.equals(userDto.username)) {
//			return true;
//		}
//		return false;
//	}
//}

package com.olx.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UserDto", description = "Data Transfer Object for User")
public class UserDto {

    @Schema(description = "User Identifier", example = "1")
    private int id;

    @Schema(description = "User Name", example = "shahab")
    private String username;

    @Schema(description = "User Password", example = "Alld@alld12")
    private String password;

    @Schema(description = "Comma-separated user roles", example = "ROLE_USER,ROLE_ADMIN")
    private String roles;

    @Schema(description = "Whether the user is active", example = "true")
    private boolean active;

    @Schema(description = "User First Name", example = "Shahabuddin")
    private String firstName;

    @Schema(description = "User Last Name", example = "Ansari")
    private String lastName;

    @Schema(description = "User Email Address", example = "shahab@example.com")
    private String email;

    public UserDto() {
        super();
    }

    public UserDto(int id, String username, String password, String roles, boolean active,
                   String firstName, String lastName, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.active = active;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='****'" +
                ", roles='" + roles + '\'' +
                ", active=" + active +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UserDto)) return false;
        UserDto userDto = (UserDto) obj;
        return username != null && username.equals(userDto.username);
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }
}

