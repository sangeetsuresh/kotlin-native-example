//
//  DataStore.swift
//  kotlinMutltiplatform
//
//  Created by Sangeet Suresh on 17/11/18.
//  Copyright © 2018 Sangeet Suresh. All rights reserved.
//

import Foundation
import common

final class DataStore{
    
    static let sharedInstance=DataStore()
    fileprivate init(){}
    var searchItems:[SearchItem]=[]
}
