import React from 'react'
import Link from 'next/link'
import Image from 'next/image'
import { Search, ShoppingBag } from 'lucide-react'
import NavigationDropdown from './navigation-dropdown'

export function Navigation({ navItems, navigationMenus, hoveredMenu, setHoveredMenu }) {
  return (
    <nav className="bg-white/95 backdrop-blur-md sticky top-0 z-50 border-b border-gray-200 relative">
      <div className="max-w-6xl mx-auto px-4">
        <div className="flex items-center justify-between h-12">
          <div className="flex items-center space-x-8">
            <Link href="/" className="text-black hover:text-gray-600 transition-colors">
              <Image 
                src="/image/apple_logo.png" 
                alt="Apple Logo" 
                width={20} 
                height={20} 
                className="w-5 h-5"
              />
            </Link>

            {navItems.map((item) => (
              <div
                key={item.key}
                className="relative"
                onMouseEnter={() => setHoveredMenu(item.key)}
                onMouseLeave={() => setHoveredMenu(null)}
              >
                <Link href={item.href} className="text-black text-xs hover:text-gray-600 transition-colors">
                  {item.name}
                </Link>
                <NavigationDropdown menuData={navigationMenus[item.key]} isVisible={hoveredMenu === item.key} />
              </div>
            ))}
          </div>
          <div className="flex items-center space-x-4">
            <Search className="w-4 h-4 text-black hover:text-gray-600 cursor-pointer transition-colors" />
            <ShoppingBag className="w-4 h-4 text-black hover:text-gray-600 cursor-pointer transition-colors" />
          </div>
        </div>
      </div>
    </nav>
  )
} 