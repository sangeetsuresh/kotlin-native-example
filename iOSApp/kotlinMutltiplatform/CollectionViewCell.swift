//
//  CollectionViewCell.swift
//  kotlinMutltiplatform
//
//  Created by Sangeet Suresh on 17/11/18.
//  Copyright © 2018 Sangeet Suresh. All rights reserved.
//

import UIKit
import common

class CollectionViewCell: UICollectionViewCell {
    @IBOutlet weak var imageView: UIImageView!
    
    @IBOutlet var title:UILabel!
    
    func displayContent(searchItem:SearchItem){
        self.title.text = searchItem.title
        self.imageView.imageFromServerURL(urlString: searchItem.poster)
    }
}
