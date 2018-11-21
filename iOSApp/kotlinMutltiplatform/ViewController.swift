//
//  ViewController.swift
//  kotlinMutltiplatform
//
//  Created by Sangeet Suresh on 04/11/18.
//  Copyright © 2018 Sangeet Suresh. All rights reserved.
//

import UIKit
import Foundation
import common

class ViewController: UIViewController,MainView,UICollectionViewDataSource,UICollectionViewDelegate,UICollectionViewDelegateFlowLayout {

    //var searchItems:[SearchItem]=[]
    
    @IBOutlet weak var collectionFlowLayout: UICollectionViewFlowLayout!
    @IBOutlet weak var mainLabel: UILabel!
    @IBOutlet weak var searchCollectionView: UICollectionView!
    @IBOutlet weak var searchTextBox: UITextField!
    @IBOutlet weak var progressBar: UIActivityIndicatorView!
    
    var presenter:MainPresenter!
    var searchTimer:Timer?
    
    let dataStore: DataStore = DataStore.sharedInstance
    
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return dataStore.searchItems.count
    }
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "collectionViewCell", for: indexPath) as! CollectionViewCell
        cell.displayContent(searchItem:dataStore.searchItems[indexPath.item])
        return cell
    }
    
    func loadList(search: SearchResult) {
        dataStore.searchItems = search.data!
        searchCollectionView?.reloadSections(IndexSet(integer: 0))
        
    }
    
    func hideProgress() {
        progressBar.stopAnimating()
    }
    
    func showNoItem() {
        //TODO
    }
    
    func showProgress() {
        self.progressBar.isHidden=false
        self.progressBar.startAnimating()
    }
    
    
    @IBAction func searchTextChanged(_ sender: UITextField) {
        dataStore.searchItems.removeAll()
        searchCollectionView?.reloadSections(IndexSet(integer: 0))
        if searchTimer != nil{
            searchTimer?.invalidate()
            searchTimer=nil
        }
        
        searchTimer=Timer.scheduledTimer(withTimeInterval: 1.0, repeats: false) { (_) in
        self.presenter.searchTitle(text:self.searchTextBox.text)
        }
        
        
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let width = view.frame.width / 3
        let height = width * 1.67
        return CGSize(width: width, height: height)
    }

    
    override func viewDidLoad() {
        super.viewDidLoad()
        searchCollectionView.delegate = self
        searchCollectionView.dataSource = self
        progressBar.isHidden = true
        progressBar.hidesWhenStopped = true
        presenter=MainPresenterImpl(mainView:self)
        
    }


    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    
}
extension UIImageView {
    public func imageFromServerURL(urlString: String) {
        self.image = nil
        URLSession.shared.dataTask(with: NSURL(string: urlString)! as URL, completionHandler: { (data, response, error) -> Void in
            
            if error != nil {
                print(error)
                return
            }
            DispatchQueue.main.async(execute: { () -> Void in
                let image = UIImage(data: data!)
                self.image = image
            })
            
        }).resume()
    }}

