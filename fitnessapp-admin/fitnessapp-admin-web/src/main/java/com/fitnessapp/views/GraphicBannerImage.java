/*
 * Copyright (C) 2018 Naluem
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.fitnessapp.views;

import java.io.FileInputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Naluem
 */
@Named(value = "graphicBannerImage")
@ViewScoped
public class GraphicBannerImage implements Serializable{

	private StreamedContent bannerImage;

	@PostConstruct
	public void init() {
		try {
			bannerImage = new DefaultStreamedContent(new FileInputStream("chartFile"), "image/png");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StreamedContent getBannerImage() {
		return bannerImage;
	}

}
