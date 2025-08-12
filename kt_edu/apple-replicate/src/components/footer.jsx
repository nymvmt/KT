import React from 'react'
import Link from 'next/link'

const footerSections = [
  {
    title: "Shop and Learn",
    links: [
      { name: "Store", href: "/store" },
      { name: "Mac", href: "/mac" },
      { name: "iPad", href: "/ipad" },
      { name: "iPhone", href: "/iphone" },
      { name: "Watch", href: "/watch" },
      { name: "Vision", href: "/vision" },
      { name: "AirPods", href: "/airpods" }
    ]
  },
  {
    title: "Apple Wallet",
    links: [
      { name: "Wallet", href: "/wallet" },
      { name: "Apple Card", href: "/apple-card" },
      { name: "Apple Pay", href: "/apple-pay" },
      { name: "Apple Cash", href: "/apple-cash" }
    ]
  },
  {
    title: "Account",
    links: [
      { name: "Manage Your Apple ID", href: "/account" },
      { name: "Apple Store Account", href: "/store/account" },
      { name: "iCloud.com", href: "/icloud" }
    ]
  },
  {
    title: "Entertainment",
    links: [
      { name: "Apple One", href: "/apple-one" },
      { name: "Apple TV+", href: "/apple-tv-plus" },
      { name: "Apple Music", href: "/apple-music" },
      { name: "Apple Arcade", href: "/apple-arcade" },
      { name: "Apple Fitness+", href: "/apple-fitness-plus" }
    ]
  },
  {
    title: "Apple Store",
    links: [
      { name: "Find a Store", href: "/retail" },
      { name: "Genius Bar", href: "/shop/goto/genius_bar" },
      { name: "Today at Apple", href: "/today" },
      { name: "Group Reservations", href: "/programs/group-reservations" }
    ]
  }
]

export function Footer() {
  return (
    <footer className="bg-gray-100 py-12">
      <div className="max-w-6xl mx-auto px-4">
        <div className="grid md:grid-cols-5 gap-8 text-sm">
          {footerSections.map((section, index) => (
            <div key={index}>
              <h4 className="font-semibold mb-4 text-gray-900">{section.title}</h4>
              <ul className="space-y-2 text-gray-600">
                {section.links.map((link, linkIndex) => (
                  <li key={linkIndex}>
                    <Link href={link.href} className="hover:text-gray-900">
                      {link.name}
                    </Link>
                  </li>
                ))}
              </ul>
            </div>
          ))}
        </div>
        <div className="border-t border-gray-300 mt-8 pt-8 text-xs text-gray-600">
          <p>Copyright © 2024 Apple Inc. All rights reserved.</p>
        </div>
      </div>
    </footer>
  )
} 