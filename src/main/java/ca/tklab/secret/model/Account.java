/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.tklab.secret.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ca.tklab.secret.model.base.BaseEntity;

@Entity
@Table(
		name = "cm_account",
		indexes = {
				@Index(name = "cm_account__usernamePassword_UIX",  columnList="username,password", unique = true)
		})
public class Account extends BaseEntity<String> {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy = "uuid")
    @Column(name = "ID", nullable = false, unique = true)
    private String id;

    @Column(name = "username", nullable = false, unique = true)
	private  String username;

    @Column(name = "password", nullable = false, unique = false)
	private  String password;

    @Column(name = "first_name", nullable = false, unique = false)
	private  String firstName;

    @Column(name = "last_name", nullable = false, unique = false)
	private  String lastName;


    @Column(name = "profile_img", nullable = true, unique = false)
	private String profileImg;

    
    @Override
    public String getId() {
        return id;
    }
    
    
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public static Builder getBuilder() {
        return new Builder();
    }
    
	

    public static class Builder {

        private Account account;

        public Builder() {
        	account = new Account();
        }
        
        public Builder username(String username) {
        	account.username = username;
            return this;
        }
        
        public Builder password(String password) {
        	account.password = password;
            return this;
        }

        public Builder firstName(String firstName) {
        	account.firstName = firstName;
            return this;
        }
        public Builder lastName(String lastName) {
        	account.lastName = lastName;
            return this;
        }

        public Account build() {
            return account;
        }
    }
}
